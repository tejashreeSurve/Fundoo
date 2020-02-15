package com.bridgelabz.userloginregistration.services.note;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.userloginregistration.dto.note.NoteDto;
import com.bridgelabz.userloginregistration.model.note.NoteDataBase;
import com.bridgelabz.userloginregistration.model.user.UserDataBase;
import com.bridgelabz.userloginregistration.repository.note.NoteRepository;
import com.bridgelabz.userloginregistration.repository.user.UserRepository;
import com.bridgelabz.userloginregistration.response.Response;
import com.bridgelabz.userloginregistration.services.user.JwtToken;

@Component
@Service
@PropertySource("message.properties")
public class NoteServiceImp implements NoteService {

	@Autowired
	JwtToken jwtOperation;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Environment environment;
	
	@Autowired
	NoteRepository noteRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public Response createNote(String token, NoteDto notedto) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"));
		NoteDataBase noteData = mapper.map(notedto, NoteDataBase.class);
		noteRepository.save(noteData);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				token,environment.getProperty("note.create"));
	}
	
	@Override
	public Response getAllNotes(String token) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"));
		List<UserDataBase> note = userRepository.findAll();
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				token,environment.getProperty("note.getallnotes"),note);
	}

	@Override
	public Response updateNote(String token, NoteDto notedto) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"));

		NoteDataBase noteData = null;
		noteData.setDescription(notedto.getDescription());
		noteData.setTitle(notedto.getTitle());
		noteRepository.save(noteData);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				token,environment.getProperty("note.update"));
	}

	@Override
	public Response deleteNote(String token, int id) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"));
		noteRepository.deleteById(id);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				token,environment.getProperty("note.delete"));
	}
}
