package com.smile.tech;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.smile.tech.model.ERole;
import com.smile.tech.model.Role;
import com.smile.tech.model.Users;
import com.smile.tech.service.UsersService;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{

	@Autowired
	UsersService service;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// TODO Auto-generated method stub
		List<Users> ls=service.findAll();
		boolean check=false;
		for(int i=0;i<ls.size();i++) {
		    if (ls != null && ls.get(i).getRoles().stream()
		      .anyMatch(a -> a.getName().equals("ROLE_SUPERADMIN"))) {
		       check=true;
		    }    
		    
		}
		if(check==false) {
			Users user=new Users();
			user.setUsername("superadmin");
			user.setPassword("123456789");
			LocalDateTime date=LocalDateTime.now();
			user.setCreatedDate(date);
			
			Role r=new Role();
			r.setId("602b9e6644cbd8da5413aa76");
			r.setName(ERole.ROLE_SUPERADMIN.name());
			
			Set<Role> role=new HashSet<>();
			role.add(r);
			user.setRoles(role);
			
			service.save(user);
			
			System.out.print("SUCCESS");
		}
		return;
	}

}
