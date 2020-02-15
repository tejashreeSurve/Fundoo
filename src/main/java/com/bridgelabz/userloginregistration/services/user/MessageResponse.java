package com.bridgelabz.userloginregistration.services.user;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
@Component
public class MessageResponse {
 public SimpleMailMessage verifyMail(String reciveremail,String recivername,String token) {
	 SimpleMailMessage message = new SimpleMailMessage();
	 message.setTo(reciveremail);
	 message.setFrom("forgotbridge70@gmail.com");
	 message.setSubject("Complete Verification!!!! ");
	 message.setText("Hi, "+recivername+ "  Your email is verify with "+ " Token :- "+ token);
	 return message;
	}
public SimpleMailMessage passwordReset(String reciveremail,String recivername,String token) {
	 SimpleMailMessage message = new SimpleMailMessage();
	 message.setTo(reciveremail);
	 message.setFrom("forgotbridge70@gmail.com");
	 message.setSubject("Reset Password!!!! ");
	 message.setText("Hi, "+recivername+ "  For reset Password, your token is "+ "  Token :- "+ token);
	 return message;
}
}
