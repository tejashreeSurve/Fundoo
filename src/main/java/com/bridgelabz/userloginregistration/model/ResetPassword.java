package com.bridgelabz.userloginregistration.model;

/**
 * @author Tejashree Surve
 * Purpose : This is Data Transfer Object that holds Data.
 */
public class ResetPassword {
	private String password;
	private String confirmpassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
}
