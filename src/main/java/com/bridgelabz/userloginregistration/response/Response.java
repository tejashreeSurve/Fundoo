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
	private String token;
	private List<UserDataBase> list;

	public Response() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public Response(int statuscode, String message) {
		this.statuscode = statuscode;
		this.message = message;
	}

	@Autowired
	public Response(int statuscode, String token, String message) {
		this.statuscode = statuscode;
		this.token=token;
		this.message=message;
	}
	@Autowired
	public Response(int statuscode, String token, String message, List<UserDataBase> note) {
		this.statuscode = statuscode;
		this.token = token;
		this.message = message;
		this.list=note;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<UserDataBase> getList() {
		return list;
	}

	public void setList(List<UserDataBase> list) {
		this.list = list;
	}
}
