package com.smile.tech.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smile.tech.model.ERole;
import com.smile.tech.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	public Optional<Role> findByName(ERole name);
}
