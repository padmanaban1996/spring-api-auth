package com.smile.tech.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smile.tech.model.Role;
import com.smile.tech.model.Users;

public class UsersDetailImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private LocalDateTime createdDate;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	@SuppressWarnings("serial")
	public UsersDetailImpl(String id, String username, LocalDateTime createdDate, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.createdDate = createdDate;
		this.password = password;
		this.authorities = authorities;
	}

	public static UsersDetailImpl build(Users user) {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new UsersDetailImpl(user.getId(), user.getUsername(), user.getCreatedDate(), user.getPassword(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public LocalDateTime getCreatedDate() {
		// TODO Auto-generated method stub
		return createdDate;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UsersDetailImpl user = (UsersDetailImpl) o;
		return Objects.equals(id, user.id);
	}

}
