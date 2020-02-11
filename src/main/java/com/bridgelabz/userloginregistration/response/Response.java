package com.bridgelabz.userloginregistration.response;

/**
 * @author Tejashree Surve
 * Purpose : This Class provide the data to send in http response.
 */
public class Response {
	private int statuscode;
	private String message;

	public Response(int statuscode, String message) {
		this.statuscode = statuscode;
		this.message = message;
	}

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
