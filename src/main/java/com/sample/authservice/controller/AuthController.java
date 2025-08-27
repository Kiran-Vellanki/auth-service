package com.sample.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.authservice.dto.LoginRequest;
import com.sample.authservice.dto.SignupRequest;
import com.sample.authservice.model.Response;
import com.sample.authservice.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


/**
 * @author KiranVellanki
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
		return Response.prepare().setMessage(authService.signup(request)).send();
	}

	@GetMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
		return Response.prepare().setMessage(authService.login(request)).send();
	}

	@GetMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestBody String refreshToken) {
		return Response.prepare().setMessage(authService.refreshToken(refreshToken)).send();
	}
}
