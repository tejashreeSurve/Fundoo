package com.bridgelabz.userloginregistration.services.user;

import com.bridgelabz.userloginregistration.dto.user.EmailForgetPasswordDto;
import com.bridgelabz.userloginregistration.dto.user.LoginUserDto;
import com.bridgelabz.userloginregistration.dto.user.ResetPasswordDto;
import com.bridgelabz.userloginregistration.dto.user.UserDto;
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
	
	Response validateUser(String token);
}
