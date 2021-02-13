package com.smile.tech.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smile.tech.model.Attendence;
import com.smile.tech.model.Users;
import com.smile.tech.repository.AttendenceRepository;

@Service
public class AttendenceService{
	
	@Autowired
	AttendenceRepository repository;

	public Attendence save(Users user) {
		
		LocalDateTime date = LocalDateTime.now();

		Attendence attendence=new Attendence();
		attendence.setStartTime(date);
		attendence.setUserID(user.getId());

		return repository.save(attendence);
	}

	public List<Attendence> findAll() {
		return repository.findAll();
	}

	public Attendence findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourecNotFoundException());
	}

	public Attendence update(Attendence attendence) {
		LocalDateTime date = LocalDateTime.now();
		attendence.setEndTime(date);
		return repository.save(attendence);
	}

	public Attendence findByUserID(String id) {
		return repository.findByUserID(id);
	}

	public List<Attendence> findUserByDate(String date) {
		return repository.findByDate(date);
	}
	
		
}
