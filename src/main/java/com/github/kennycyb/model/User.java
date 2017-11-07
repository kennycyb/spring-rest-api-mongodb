package com.github.kennycyb.model;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	public String id;
	
	public String firstName;
	public String lastName;
	
	public String userName;
	public String passwordHash;
	
	public User() {}
	
	public User(String userName, String firstName, String lastName) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String toString() {
		return String.format("User[id=%s, firstName=%s, lastName=%s", id, firstName, lastName);
	}
}
