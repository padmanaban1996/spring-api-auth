package com.smile.tech.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.tech.model.Role;
import com.smile.tech.model.Users;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTests {

	private static final String ATT_ID = "8734823";
	private static final String USER_ID = "7823728";
	private static final String USER_NAME = "User2";
	private static final String PASSWORD = "123456789";
	static LocalDateTime ldt = LocalDateTime.now();
	private static final LocalDateTime CREATEDDATE = ldt;
	private static final Set<Role> ROLE = null;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Before
	public void setup() {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}
	
	@WithMockUser(username = "admin")
	@Test
	public void savePerson() throws Exception {
        Role r=new Role();
		r.setId("60238313a156a4082d124c6f");
		r.setName("ROLE_ADMIN");
		
		Set<Role> role=new HashSet<>();
		role.add(r);
		
		Users user=new Users();
		//user.setId("602753e2ba5b6a619746e1d7");
		user.setUsername(USER_NAME);
		user.setPassword(PASSWORD);
	//	user.setCreatedDate(CREATEDDATE);
	//	user.setRoles(role);
		String jsonreq = mapper.writeValueAsString(user);
		
		System.out.println("json value"+">>-->"+jsonreq);
	    MvcResult resul=mockMvc.perform(post("/auth/user/signup").content(jsonreq)
	    		      .contentType(MediaType.APPLICATION_JSON))
	                 .andExpect(status().isOk()).andReturn();
	    
	assertEquals(200, resul.getResponse().getStatus());
	}
}
