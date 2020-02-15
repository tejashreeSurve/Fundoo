package com.bridgelabz.userloginregistration.services.user;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.bridgelabz.userloginregistration.Exception.custom.ForgetPasswordException;
import com.bridgelabz.userloginregistration.Exception.custom.LoginException;
import com.bridgelabz.userloginregistration.Exception.custom.ResetPasswordException;
import com.bridgelabz.userloginregistration.Exception.custom.ValidateException;
import com.bridgelabz.userloginregistration.dto.user.EmailForgetPasswordDto;
import com.bridgelabz.userloginregistration.dto.user.LoginUserDto;
import com.bridgelabz.userloginregistration.dto.user.ResetPasswordDto;
import com.bridgelabz.userloginregistration.dto.user.UserDto;
import com.bridgelabz.userloginregistration.model.user.UserDataBase;
import com.bridgelabz.userloginregistration.repository.user.UserRepository;
import com.bridgelabz.userloginregistration.response.Response;

/**
 * @author Tejashree Surve Purpose : This Class contains Logic for ever RestApi
 *         methods.
 */
@Service
@PropertySource("message.properties")
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JwtToken jwtobject;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private MessageResponse messageResponse;

	private SimpleMailMessage email;
	@Autowired
	private Environment environment;
	@Autowired
	private MessageInfo message;

	// Login operation
	@Override
	public Response login(LoginUserDto loginUser) {
		UserDataBase user = userRepository.findByEmail(loginUser.getEmail());
		String token = jwtobject.generateToken(loginUser.getEmail());
		System.out.println(token);
		if (user == null)
			throw new LoginException(message.User_Not_Exist);
		if (user.getIsValidate()) {
			if ((user.getUserpassword()).equals(loginUser.getUserpassword())) {
				return new Response(Integer.parseInt(environment.getProperty("status.success.code")), token,
						environment.getProperty("status.login.success"));
			} else {
				return new Response(Integer.parseInt(environment.getProperty("status.redirect.code")),
						environment.getProperty("status.password.incorrect"));
			}
		} else {
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notverify"));
		}
	}

	// For Verification
	@Override
	public Response validateUser(String token) {
		String email = jwtobject.getToken(token);
		UserDataBase userIsVarified = userRepository.findByEmail(email);
		if (userIsVarified == null) {
			throw new ValidateException(environment.getProperty("status.email.notexist"));
		} else {
			userIsVarified.setIsValidate(true);
			userRepository.save(userIsVarified);
//			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
//					environment.getProperty("status.email.isverify"));
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")), environment.getProperty("status.email.isverify"));
		}
	}

	// Registration operation
	@Override
	public Response registration(UserDto user) {
		String emailIsPresent = user.getEmail();
		UserDataBase userIsPresent = userRepository.findByEmail(emailIsPresent);
		if (userIsPresent != null) {
			return new Response(Integer.parseInt(environment.getProperty("status.email.isexist")),
					"email already Exist");
		}
		UserDataBase userdata = mapper.map(user, UserDataBase.class);
		userRepository.save(userdata);
		String token = jwtobject.generateToken(userdata.getEmail());
		System.out.println(token);
		email = messageResponse.verifyMail(userdata.getEmail(), userdata.getFirstname(), token);
		emailSenderService.sendEmail(email);

		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("status.user.register"));
	}

	// Forget Password operation
	@Override
	public Response forgetPassword(EmailForgetPasswordDto emailForgetPassword) {
		UserDataBase user = userRepository.findByEmail(emailForgetPassword.getEmail());
		if (user == null)
			throw new ForgetPasswordException(environment.getProperty("status.email.notexist"));
		if (user.getIsValidate()) {
			String token = jwtobject.generateToken(emailForgetPassword.getEmail());
			UserDataBase userdata = userRepository.findByEmail(emailForgetPassword.getEmail());
			System.out.println(token);
			email = messageResponse.passwordReset(emailForgetPassword.getEmail(), userdata.getFirstname(), token);
			emailSenderService.sendEmail(email);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("status.token.send"));
		} else {
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notverify"));
		}
	}

	// Reset Password operation
	@Override
	public Response resetPassword(String token, ResetPasswordDto passwordreset) {
		String checkEmail = jwtobject.getToken(token);
		System.out.println(checkEmail);
		UserDataBase userUpdate = userRepository.findByEmail(checkEmail);
		if (userUpdate == null)
			throw new ResetPasswordException(environment.getProperty("status.email.notexist"));
		if (passwordreset.getConfirmpassword().equals(passwordreset.getPassword())) {
			userUpdate.setUserpassword(passwordreset.getPassword());
			userRepository.save(userUpdate);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("status.password.update"));
		} else {
			return new Response(Integer.parseInt(environment.getProperty("status.redirect.code")),
					environment.getProperty("status.password.incorrect"));
		}
	}
}
