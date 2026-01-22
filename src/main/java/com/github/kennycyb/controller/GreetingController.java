package com.github.kennycyb.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.kennycyb.model.Greeting;

/**
 * Controller for handling greeting requests.
 * This REST controller provides endpoints for generating personalized greetings.
 */
@RestController
public class GreetingController {

	// Template for the greeting message, using %S to format the name in uppercase
	private static final String TEMPLATE = "HELLO, %S!";

	// Atomic counter to generate unique IDs for each greeting
	private final AtomicLong counter = new AtomicLong();

	/**
	 * Handles GET requests to /greeting endpoint.
	 * Returns a greeting message with an incremented counter.
	 *
	 * @param name the name to greet, defaults to "World" if not provided
	 * @return a Greeting object containing an ID and the formatted greeting content
	 */
	@RequestMapping("/greeting")
	public Greeting greeting(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE,
				name));
	}
}
