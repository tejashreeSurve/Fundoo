package com.bridgelabz.userloginregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userloginregistration.dto.user.EmailForgetPasswordDto;
import com.bridgelabz.userloginregistration.dto.user.LoginUserDto;
import com.bridgelabz.userloginregistration.dto.user.ResetPasswordDto;
import com.bridgelabz.userloginregistration.dto.user.UserDto;
import com.bridgelabz.userloginregistration.response.Response;
import com.bridgelabz.userloginregistration.services.user.UserService;

/**
 * @author Tejashree Surve
 * Purpose : This is RESTAPI Controller which contains multiple Api.
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	// Register RestApi method
	@PostMapping("/userRegistration/register")
	public ResponseEntity<Response> registrationUser(@RequestBody UserDto user) {
		Response response = userService.registration(user);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// Login RestApi method
	@PostMapping("/userRegistration/login")
	public ResponseEntity<Response> login(@RequestBody LoginUserDto loginUser) {
		Response response = userService.login(loginUser);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// Forget password RestApi method
	@PostMapping("/userRegistration/forgetPassword")
	public ResponseEntity<Response> forgetPassword(@RequestBody EmailForgetPasswordDto emailforgetpassword) {
		Response response = userService.forgetPassword(emailforgetpassword);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// Reset Password RestApi method
	@PutMapping(value = "/userRegistration/resetPassword")
	public ResponseEntity<Response> resetPassword(@RequestHeader String token, @RequestBody ResetPasswordDto password) {
		Response response = userService.resetPassword(token, password);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	// User Validation RestApi method
	@PostMapping("/userRegistration/validation")
	public ResponseEntity<Response> validation(@RequestHeader String token){
		Response response = userService.validateUser(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
