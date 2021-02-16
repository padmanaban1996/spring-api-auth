package com.smile.tech.payload.response;

import java.time.LocalDateTime;
import java.util.List;

public class JwtResponse {

	private String accessToken;
	private String tokenType = "Bearer";
	private String id;
	private String username;
	private LocalDateTime createdDate;

	private List<String> roles;

	public JwtResponse() {

	}

	public JwtResponse(String id, String username, LocalDateTime createdDate, List<String> roles, String jwt) {

		this.id = id;
		this.username = username;
		this.createdDate = createdDate;
		this.roles = roles;
		this.accessToken = jwt;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
