package com.upms.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserCreateForm {
	@NotEmpty(message = "email is empty")
	private String	email;
	@NotEmpty(message = "password is empty")
	private String	password;
	@NotEmpty(message = "password is empty")
	private String	passwordRepeated;
	@NotNull
	private Role	role	= Role.USER;



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getPasswordRepeated() {
		return passwordRepeated;
	}



	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "UserCreateForm{" + "email='" + email + '\'' + ", password='" + password + '\'' + ", passwordRepeated='"
				+ passwordRepeated + '\'' + ", role=" + role + '}';
	}
}
