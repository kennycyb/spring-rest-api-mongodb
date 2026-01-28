package com.github.kennycyb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.kennycyb.model.User;
import com.github.kennycyb.model.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository repository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public ResponseEntity<User> login(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {

		if (username == null || password == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		User user = repository.findByUserName(username);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		if (!passwordEncoder.matches(password, user.passwordHash)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		return ResponseEntity.ok(user);
	}

	@RequestMapping("/users")
	public List<User> index() {
		return repository.findAll();
	}

}
