package com.smile.tech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smile.tech.model.Users;
import com.smile.tech.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	UsersRepository repository;

	public List<Users> findAll() {
		return repository.findAll();
	}

	public Users findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourecNotFoundException());
	}

	public Users save(Users users) {
		return repository.save(users);
	}

	public void delete(Users users) {
		repository.delete(users);
	}

	public Users findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public Users findByRoles(String roles) {
		return repository.findByRoles(roles);
	}

}
