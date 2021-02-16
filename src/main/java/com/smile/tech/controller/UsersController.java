package com.smile.tech.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.smile.tech.model.Attendence;
import com.smile.tech.model.Users;
import com.smile.tech.service.AttendenceService;
import com.smile.tech.service.UsersService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	UsersService service;

	@Autowired
	AttendenceService AService;

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/all")
	public List<Users> getAllUsers() {
		return service.findAll();
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/{id}")
	public Users getUserById(@PathVariable String id) {
		return service.findById(id);
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/find/{username}")
	public Users getUserByUsername(@PathVariable("username") String username) {
		return service.findByUsername(username);
	}

//	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//	@DeleteMapping(value = "/{id}")
//	@ResponseStatus(code = HttpStatus.ACCEPTED)
//	public void deleteUser(@PathVariable String id) {
//		Users users = service.findById(id);
//		service.delete(users);
//	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/attend/{id}")
	public ResponseEntity<?> userAttendenceEntry(@PathVariable String id) {
		Users user = service.findById(id);
		List<Attendence> attendence = AService.findAll();
		return AService.attendenceRecord(user, attendence);
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/attend/{date}")
	public List<Attendence> findAttendenceByDate(@PathVariable String date) {
		List<Attendence> ls = AService.findAll();
		return AService.findListByDate(ls, date);
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/attend/{week}/{year}")
	public List<Attendence> findAttendenceByYearandWeek(@PathVariable("week") int week,
			@PathVariable("year") int year) {
		List<Attendence> ls = AService.findAll();
		
		
		return AService.findLisByDayandYear(ls,week,year);
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/attend/month/{month}")
	public List<Attendence> findAttendenceByMonth(@PathVariable int month) {
		List<Attendence> ls = AService.findAll();
		return AService.findListByMonth(ls, month);
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/absent/{month}")
	public List<Attendence> findAbsentReport(@PathVariable int month) {
		List<Users> users = service.findAll();
		return AService.findAbsentListByMonth(users, month);
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/present/{month}")
	public List<Attendence> findPresentReport(@PathVariable int month) {
		List<Users> users = service.findAll();
		return AService.findPresentListByMonth(users, month);
	}

}
