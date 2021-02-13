package com.smile.tech.payload.request;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotBlank;

public class SignupRequest {

	@NotBlank
	private String username;

	private LocalDateTime createdDate;

	@NotBlank
	private String password;

	private Set<String> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
