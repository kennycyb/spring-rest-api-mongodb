package com.github.kennycyb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.kennycyb.model.User;
import com.github.kennycyb.model.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository repository;

	@RequestMapping("/user/login")
	public User login(String username, String password)
	{
		// TODO:
		if (username == null) {
			username = "test";
		}
		
		User user = repository.findByUserName(username);
		
		// TODO: create a test user if not found
		if (user == null) {
			user = new User("test", "Hello", "World");
			repository.save(user);
		}
		
		return user;
	}
	
	@RequestMapping("/users")
	public List<User> index() {
		return repository.findAll();
	}
	
}
