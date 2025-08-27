package com.sample.authservice.entity;

/**
 * 
 */
public enum Role {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

	private final String role;

	private Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}
}
