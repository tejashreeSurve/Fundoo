package com.bridgelabz.userloginregistration.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	//@Override
	/*
	 * public Response login(LoginUser loginUser) { UserDto userDto;
	 * loginUser.getEmail(); userDto.getEmail(); }
	 */
	@Override
	public Response registration(UserDto user) {
		UserDataBase userdata=mapper.map(user, UserDataBase.class);
		userRepository.save(userdata);
		Response response=new Response(200,"register Successcully");
		return response;
	}

}
