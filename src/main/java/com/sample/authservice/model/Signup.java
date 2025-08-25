package com.sample.authservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


/**
 * @author KiranVellanki
 */
@Data
public class Signup {

	@NotBlank(message = "user name mandatory!")
	@Size(min = 6, max = 16)
	private String username;
	@NotBlank(message = "password mandatory!")
	@Size(min = 6, max = 12)
	private String password;
	@NotBlank(message = "password mandatory!")
	@Email
	private String mail;
	@NotBlank(message = "phone number mandatory!")
	@Pattern(regexp = "^\\\\d{10}$", message = "invalid phone number")
	private String phnNo;

}
