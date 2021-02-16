package com.smile.tech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smile.tech.model.Users;
import com.smile.tech.repository.UsersRepository;

@Service
public class UsersDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository repository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repository.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with this username: " + username));

		return UsersDetailImpl.build(user);
	}

}
