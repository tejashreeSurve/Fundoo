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

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JwtToken jwtobject;

	// @Override
	public Response login(LoginUser loginUser) {
		UserDataBase user = userRepository.findByEmail(loginUser.getEmail());
		if(user == null)
			return new Response(400,"Email-id dosen't exist,Register First!!!");
		if ((user.getUserpassword()).equals(loginUser.getUserpassword())) {
			return new Response(200, "login-in successfully");
		} else {
			return new Response(400, "incorrect password");
		}
	
	}

	@Override
	public Response registration(UserDto user) {
		UserDataBase userdata = mapper.map(user, UserDataBase.class);
		userRepository.save(userdata);
		return new Response(200, "register Successcully");
	}

	@Override
	public Response forgetPassword(EmailForgetPassword emailForgetPassword) {
		String token = jwtobject.generateToken(emailForgetPassword.getEmail());
		System.out.println(token);
		return new Response(200, "Authorization done ");
	}

	@Override
	public Response resetPassword(String token, ResetPassword passwordreset) {
		System.out.println("*****"+token);
		System.out.println("*****"+passwordreset);
		String checkEmail = jwtobject.getToken(token);
		UserDataBase userUpdate = userRepository.findByEmail(checkEmail);
		if(userUpdate == null)
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
