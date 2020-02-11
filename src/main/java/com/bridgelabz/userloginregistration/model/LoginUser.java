package com.bridgelabz.userloginregistration.model;
/**
 * @author Tejashree Surve
 * Purpose : This is Data Transfer Object that holds Data.
 */
public class LoginUser {
	private String email;
	private String userpassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
}
