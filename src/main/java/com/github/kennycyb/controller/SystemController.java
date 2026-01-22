package com.github.kennycyb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.kennycyb.model.General;

/**
 * REST Controller for system health check endpoints.
 * Provides basic system status information for monitoring and health checks.
 */
@RestController
public class SystemController {

	/**
	 * Endpoint to check if the system is ready to accept requests.
	 * This is typically used by load balancers or orchestration systems
	 * to determine if the application should receive traffic.
	 *
	 * @return General object with "OK" status indicating the system is ready
	 */
	@RequestMapping("/system/ready")
	public General ready() {
		return new General("OK");
	}

	/**
	 * Endpoint to check if the system is alive and responding.
	 * This is a basic liveness probe that confirms the application
	 * is running and can handle HTTP requests.
	 *
	 * @return General object with "ALIVE" status indicating the system is alive
	 */
	@RequestMapping("/system/alive")
	public General alive() {
		return new General("ALIVE");
	}
}
