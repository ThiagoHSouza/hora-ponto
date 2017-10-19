package br.com.developer.horaponto.users.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {
	private String username;
	private String password;
	private String fullname;
}
