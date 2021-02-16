package com.smile.tech.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.smile.tech.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

	public Optional<Users> findUserByUsername(String username);

	boolean existsByUsername(String username);

	public Users findByRoles(String roles);

	public Users findByUsername(String username);

}
