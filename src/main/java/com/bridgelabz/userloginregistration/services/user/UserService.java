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
	// login
	Response login(LoginUserDto loginUser);

	// registration
	Response registration(UserDto user);

	// forget password
	Response forgetPassword(EmailForgetPasswordDto email);

	// reset password
	Response resetPassword(String token, ResetPasswordDto passwordrest);
	
	// validate user
	Response validateUser(String token);
}
