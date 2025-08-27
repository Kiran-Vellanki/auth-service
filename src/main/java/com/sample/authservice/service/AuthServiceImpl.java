package com.sample.authservice.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sample.authservice.dto.AuthResponse;
import com.sample.authservice.entity.Role;
import com.sample.authservice.dto.LoginRequest;
import com.sample.authservice.dto.SignupRequest;
import com.sample.authservice.entity.UserData;
import com.sample.authservice.repository.UserDataRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author KiranVellanki
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserDataRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Override
	public AuthResponse signup(SignupRequest request) {
		// Check if username or email already exists
		if (userRepository.existsByUsername(request.getUsername())) {
			return AuthResponse.builder().message("Username already exists").build();
		}
		if (userRepository.existsByEmail(request.getEmail())) {
			return AuthResponse.builder().message("Email already exists").build();
		}

		// Create new user
		UserData user = new UserData();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setRole(Role.USER); // Set default role as USER

		userRepository.save(user);

		// Generate tokens
		String jwtToken = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), java.util.Collections.emptyList()));

		return AuthResponse.builder().token(jwtToken)

				.message("User registered successfully").build();
	}

	@Override
	public AuthResponse login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		UserData user = userRepository.findByUsername(request.getUsername()).orElseThrow();

		String jwtToken = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), java.util.Collections.emptyList()));

		return AuthResponse.builder().token(jwtToken)

				.message("Login successful").build();
	}

	@Override
	public AuthResponse refreshToken(String refreshToken) {
		String username = jwtService.extractUsername(refreshToken);
		var user = userRepository.findByUsername(username).orElseThrow();

		if (jwtService.isTokenValid(refreshToken, new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), java.util.Collections.emptyList()))) {
			String accessToken = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), java.util.Collections.emptyList()));

			return AuthResponse.builder().token(accessToken).token(refreshToken)
					.message("Token refreshed successfully").build();
		}

		return AuthResponse.builder().message("Invalid refresh token").build();
	}
}
