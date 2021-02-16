package com.smile.tech.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {
	@Id
	private String id;

	@NotBlank
	@Indexed(unique = true)
	private String username;

	@NotBlank
	private LocalDateTime createdDate;

	@Min(8)
	@Max(16)
	@NotBlank
	private String password;

	@DBRef
	private Set<Role> roles;
 
	public Users() {

	}

	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Users(String username, LocalDateTime createdDate, String password, Set<Role> role) {
		super();
		this.username = username;
		this.createdDate = createdDate;
		this.password = password;
		this.roles = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> userRole) {
		this.roles = userRole;
	}
}