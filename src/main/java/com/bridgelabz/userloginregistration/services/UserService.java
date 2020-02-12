package com.bridgelabz.userloginregistration.services;

import com.bridgelabz.userloginregistration.dto.EmailForgetPasswordDto;
import com.bridgelabz.userloginregistration.dto.LoginUserDto;
import com.bridgelabz.userloginregistration.dto.ResetPasswordDto;
import com.bridgelabz.userloginregistration.dto.UserDto;
import com.bridgelabz.userloginregistration.response.Response;

/**
 * @author Tejashree Surve
 * Purpose : Interface of UserServices methods.
 */
public interface UserService {
	
	Response login(LoginUserDto loginUser);

	Response registration(UserDto user);

	Response forgetPassword(EmailForgetPasswordDto email);

	Response resetPassword(String token, ResetPasswordDto passwordrest);
}
