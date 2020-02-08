package com.bridgelabz.userloginregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userloginregistration.model.LoginUser;
import com.bridgelabz.userloginregistration.model.UserDto;
import com.bridgelabz.userloginregistration.response.Response;
import com.bridgelabz.userloginregistration.services.UserService;


@RestController
public class Controller {

	@Autowired 
	private UserService userService;
	@RequestMapping(method = RequestMethod.POST,value = "/userRegistration/register")
	public ResponseEntity<Response> registrationUser(@RequestBody UserDto user){
		Response response=userService.registration(user);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	/*
	 * @RequestMapping(method = RequestMethod.POST,value="/userRegistration/login")
	 * public ResponseEntity<Response> login(@RequestBody LoginUser loginUser){
	 * Response response=userService.login(loginUser); return new
	 * ResponseEntity<Response>(response,HttpStatus.OK); }
	 */
}
