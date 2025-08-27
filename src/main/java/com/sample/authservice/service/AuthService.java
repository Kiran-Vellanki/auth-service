package com.sample.authservice.service;

import com.sample.authservice.dto.AuthResponse;
import com.sample.authservice.dto.LoginRequest;
import com.sample.authservice.dto.SignupRequest;

/**
 * @author KiranVellanki
 */
public interface AuthService {

	public AuthResponse signup(final SignupRequest signupRequest);

	public AuthResponse login(final LoginRequest loginRequest);

	public AuthResponse refreshToken(final String token);
}
