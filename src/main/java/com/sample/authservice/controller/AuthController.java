package com.sample.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.authservice.model.Response;
import com.sample.authservice.model.Signup;

/**
 * @author KiranVellanki
 */
@RestController
@RequestMapping("auth/")
public class AuthController {

	@PostMapping("signup")
	public ResponseEntity<?> signup(@RequestBody Signup signup) {
		return Response.prepare().setMessage(signup.getUsername()).setStatus(HttpStatus.OK).send();
	}

}
