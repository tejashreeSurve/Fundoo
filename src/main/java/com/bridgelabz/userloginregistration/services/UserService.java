package com.bridgelabz.userloginregistration.services;

import com.bridgelabz.userloginregistration.model.EmailForgetPassword;
import com.bridgelabz.userloginregistration.model.LoginUser;
import com.bridgelabz.userloginregistration.model.ResetPassword;
import com.bridgelabz.userloginregistration.model.UserDto;
import com.bridgelabz.userloginregistration.response.Response;

/**
 * @author Tejashree Surve
 * Purpose : Interface of UserServices methods.
 */
public interface UserService {
	
	Response login(LoginUser loginUser);

	Response registration(UserDto user);

	Response forgetPassword(EmailForgetPassword email);

	Response resetPassword(String token, ResetPassword passwordrest);
}
