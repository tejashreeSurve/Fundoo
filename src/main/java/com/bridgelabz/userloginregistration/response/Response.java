package com.bridgelabz.userloginregistration.response;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.userloginregistration.model.user.UserDataBase;

/**
 * @author Tejashree Surve
 * Purpose : This Class provide the data to send in http response.
 */
public class Response {
	private int statuscode;
	private String message;
	private Object data;

	public Response() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public Response(int statuscode, String message, Object data) {
		this.statuscode = statuscode;
		this.message = message;
		this.data = data;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
