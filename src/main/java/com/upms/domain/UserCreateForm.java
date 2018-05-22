package com.upms.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserCreateForm {
	@NotEmpty
	private String	email;
	@NotEmpty
	private String	password;
	@NotEmpty
	private String	passwordRePeated;
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



	public String getPasswordRePeated() {
		return passwordRePeated;
	}



	public void setPasswordRePeated(String passwordRePeated) {
		this.passwordRePeated = passwordRePeated;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "UserCreateForm{" + "email='" + email + '\'' + ", password='" + password + '\'' + ", passwordRePeated='"
				+ passwordRePeated + '\'' + ", role=" + role + '}';
	}
}
