package com.smile.tech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.smile.tech.model.Attendence;
import com.smile.tech.model.Users;

public interface AttendenceRepository extends MongoRepository<Attendence, String> {

	public Attendence findByUserID(String userID);
	//public List<Attendence> findByDate(String date);
	
}
