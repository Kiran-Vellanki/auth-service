package com.sample.authservice.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author KiranVellanki
 * 
 * @param <T>
 */
public class Response<T> {
	private T message;
	private HttpStatus status = HttpStatus.OK;

	public static <T> Response<T> prepare() {
		return new Response<>();
	}

	public Response<T> setMessage(T message) {
		this.message = message;
		return this;
	}

	public Response<T> setStatus(HttpStatus status) {
		this.status = status;
		return this;
	}

	public Response<T> setStatusByCode(int code) {
		this.status = HttpStatus.valueOf(code);
		return this;
	}

	public ResponseEntity<?> send() {
		return ResponseEntity.status(status).body(message);
	}
}
