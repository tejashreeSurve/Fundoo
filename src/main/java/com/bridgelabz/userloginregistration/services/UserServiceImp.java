package com.bridgelabz.userloginregistration.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.userloginregistration.model.EmailForgetPassword;
import com.bridgelabz.userloginregistration.model.LoginUser;
import com.bridgelabz.userloginregistration.model.ResetPassword;
import com.bridgelabz.userloginregistration.model.UserDataBase;
import com.bridgelabz.userloginregistration.model.UserDto;
import com.bridgelabz.userloginregistration.repository.UserRepository;
import com.bridgelabz.userloginregistration.response.Response;

/**
 * @author Tejashree Surve 
 * Purpose : This Class contains Logic for ever RestApi methods.
 */
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JwtToken jwtobject;

	// Login operation
	@Override
	public Response login(LoginUser loginUser) {
		UserDataBase user = userRepository.findByEmail(loginUser.getEmail());
		if (user == null)
			return new Response(400, "Email-id dosen't exist,Register First!!!");
		if ((user.getUserpassword()).equals(loginUser.getUserpassword())) {
			return new Response(200, "login-in successfully");
		} else {
			return new Response(400, "incorrect password");
		}
	}

	// Registration operation
	@Override
	public Response registration(UserDto user) {
		UserDataBase userdata = mapper.map(user, UserDataBase.class);
		userRepository.save(userdata);
		return new Response(200, "register Successcully");
	}

	// Forget Password operation
	@Override
	public Response forgetPassword(EmailForgetPassword emailForgetPassword) {
		String token = jwtobject.generateToken(emailForgetPassword.getEmail());
		System.out.println(token);
		return new Response(200, "Authorization done ");
	}

	// Reset Password operation
	@Override
	public Response resetPassword(String token, ResetPassword passwordreset) {
		String checkEmail = jwtobject.getToken(token);
		System.out.println(checkEmail);
		UserDataBase userUpdate = userRepository.findByEmail(checkEmail);
		if (userUpdate == null)
			return new Response(400, "invalide token generation");
		if (passwordreset.getConfirmpassword().equals(passwordreset.getPassword())) {
			System.out.println("im in");
			userUpdate.setUserpassword(passwordreset.getPassword());
			userUpdate.setConfirmpassword(passwordreset.getConfirmpassword());
			userRepository.save(userUpdate);
			return new Response(200, "password is update");
		} else {
			return new Response(400, "password is incorrect");
		}
	}
}
