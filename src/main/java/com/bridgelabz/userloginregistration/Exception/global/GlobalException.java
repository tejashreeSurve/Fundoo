package com.bridgelabz.userloginregistration.Exception.global;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.userloginregistration.Exception.custom.*;
import com.bridgelabz.userloginregistration.response.Response;
@ControllerAdvice
public class GlobalException {
	Environment environment;
@ExceptionHandler(ForgetPasswordException.class)
public ResponseEntity<Response> ForgetPasswordException(Exception e){
	return new ResponseEntity<Response>(new Response(Integer.parseInt(environment.getProperty("status.bad.code")),e.getMessage()), HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(LoginException.class)
public ResponseEntity<Response> LoginException(){
	return new ResponseEntity<Response>(new Response(Integer.parseInt(environment.getProperty("status.bad.code")),environment.getProperty("status.email.notexist")), HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(RegistrationException.class)
public ResponseEntity<Response> RegistrationException(){
	return new ResponseEntity<Response>(new Response(Integer.parseInt(environment.getProperty("status.bad.code")),environment.getProperty("status.email.notexist")), HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(ResetPasswordException.class)
public ResponseEntity<Response> ResetPasswordException(){
	return new ResponseEntity<Response>(new Response(Integer.parseInt(environment.getProperty("status.bad.code")),environment.getProperty("status.email.notexist")), HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(ValidateException.class)
public ResponseEntity<Response> ValidateException(Exception e){
	return new ResponseEntity<Response>(new Response(Integer.parseInt(environment.getProperty("status.bad.code")),e.getMessage()), HttpStatus.BAD_REQUEST);
}
}
