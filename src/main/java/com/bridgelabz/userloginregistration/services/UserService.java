package com.bridgelabz.userloginregistration.services;

import com.bridgelabz.userloginregistration.model.LoginUser;
import com.bridgelabz.userloginregistration.model.UserDto;
import com.bridgelabz.userloginregistration.response.Response;

public interface UserService {
	/* Response login(LoginUser loginUser); */
Response registration(UserDto user);
}
