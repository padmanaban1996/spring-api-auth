package com.smile.tech.controller;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.IndexOfArray;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smile.tech.model.Attendence;
import com.smile.tech.model.Users;
import com.smile.tech.service.AttendenceService;
import com.smile.tech.service.UsersService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class UsersController {

	@Autowired
	UsersService service;

	@Autowired
	AttendenceService AService;

	@GetMapping("/all")
	public List<Users> getAllusers() {
		return service.findAll();
	}

	@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/{id}")
	public Users getUserById(@PathVariable String id) {
		return service.findById(id);
	}

	@GetMapping("/find/{username}")
	public Users getUserByUsername(@PathVariable String username) {
		return service.findByUsername(username);
	}

	@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public Users update(@PathVariable String id, @RequestBody Users userUpdate) {
		Users users = service.findById(id);
		users.setUsername(userUpdate.getUsername());
		users.setCreatedDate(userUpdate.getCreatedDate());
		return service.save(users);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteUser(@PathVariable String id) {
		Users users = service.findById(id);
		service.delete(users);
	}

	//this is the error page
	@PostMapping("/attend/{id}")
	public Attendence userAttendenceStart(@PathVariable String id) {
		Users user = service.findById(id);
		//Attendence attendence = AService.findByUserID(id);{
		List<Attendence> attendence=AService.findAll();
		
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
		if (attendence == null) {
			return AService.save(user);
		}
		for(int i=0;i<attendence.size();i++) {
		if (user.getId().equals(attendence.get(i).getUserID())) { 
			if (attendence.get(i).getDate().equals(today)) {
				return AService.update(attendence.get(i));
			}
			else if(!attendence.get(i).getDate().equals(today)){
				return AService.save(user);
			}
			else {
				continue;
			}
		} else {
			continue;
		}
		}
		return null;
	}

	@GetMapping("/attend/{date}")
	public List<Object> findByDate(@PathVariable String date) {
		List<Attendence> ls = AService.findAll();
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getDate().equals(date)) {
				list.add(ls.get(i));
			}
		}
		return list;
	}

	@GetMapping("/attend/{week}/{year}")
	public List<Object> findByYrNdWeek(@PathVariable("week") DayOfWeek week, @PathVariable("year") int year) {
		List<Attendence> ls = AService.findAll();
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getYear() == year) {
				if (ls.get(i).getDayofWeek() == week)
					list.add(ls.get(i));
			}
		}
		return list;
	}

	@GetMapping("/attend/month/{month}")
	public List<Object> findByMonth(@PathVariable int month) {
		List<Attendence> ls = AService.findAll();
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getMonth() == month) {
				list.add(ls.get(i));
			}
		}
		return list;
	}
	
	

}
