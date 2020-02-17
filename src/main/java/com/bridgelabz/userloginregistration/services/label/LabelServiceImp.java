package com.bridgelabz.userloginregistration.services.label;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.userloginregistration.dto.label.LabelDto;
import com.bridgelabz.userloginregistration.model.label.LabelDataBase;
import com.bridgelabz.userloginregistration.model.note.NoteDataBase;
import com.bridgelabz.userloginregistration.model.user.UserDataBase;
import com.bridgelabz.userloginregistration.repository.label.LabelRepository;
import com.bridgelabz.userloginregistration.repository.note.NoteRepository;
import com.bridgelabz.userloginregistration.repository.user.UserRepository;
import com.bridgelabz.userloginregistration.response.Response;
import com.bridgelabz.userloginregistration.services.user.JwtToken;
import com.bridgelabz.userloginregistration.services.user.MessageInfo;

@Component
@Service
@PropertySource("message.properties")
public class LabelServiceImp implements LabelService {
	
	@Autowired
	JwtToken jwttoken;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	Environment environment;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired 
	MessageInfo message;

	@Override
	public Response addLabel(String token,int id, LabelDto labeldto) {
        String email = jwttoken.getToken(token);
        UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"),message.User_Not_Exist);
		NoteDataBase notedata = noteRepository.findById(id);
		List<NoteDataBase> list = new ArrayList<NoteDataBase>();
		list.add(notedata);
		if(notedata == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		else {
		LabelDataBase labeldatabase = mapper.map(labeldto, LabelDataBase.class) ;
		labeldatabase.setNoteList(list);
		System.out.println(labeldatabase.getLabelname());
		System.out.println(labeldatabase.getNoteList().get(0).getId());
		labelRepository.save(labeldatabase);
		return  new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("label.create"),token);
		}
	}

	@Override
	public Response getAlllabel(String token) {
		String email = jwttoken.getToken(token);
        UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"),message.User_Not_Exist);
		
		List<LabelDataBase> labelList = labelRepository.findAll();
		
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("label.getAllLabels"),labelList);
	}

	@Override
	public Response updateLabel(String token,int id, LabelDto labeldto) {
		String email = jwttoken.getToken(token);
        UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"),message.User_Not_Exist);
		LabelDataBase labedata= labelRepository.findById(id);
		// add here id == null 
		labelRepository.save(labedata);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("label.update"),token);
	}

	@Override
	public Response deleteLabel(String token, int id) {
		String email = jwttoken.getToken(token);
        UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
				environment.getProperty("status.email.notexist"),message.User_Not_Exist);
	// add here id ==null 
		labelRepository.deleteById(id);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("label.delete"),token);
	}

}
