package com.bridgelabz.userloginregistration.services.note;

import java.util.ArrayList;
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
import com.bridgelabz.userloginregistration.services.user.MessageInfo;

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

	@Autowired
	MessageInfo message;
	
	@Autowired
	UserDataBase userdatabase;

	// Create New Note
	@Override
	public Response createNote(String token, NoteDto notedto) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		NoteDataBase noteData = mapper.map(notedto, NoteDataBase.class);
		noteData.setUserDataBase(user);
		noteRepository.save(noteData);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.create"), token);
	}

	// Get all Notes
	@Override
	public Response getAllNotes(String token) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		List<NoteDataBase> allNotedata = noteRepository.findAll();
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		if(allNotedata == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		List<NoteDataBase> notedata= new ArrayList<NoteDataBase>();
		for(int i=0;i<allNotedata.size();i++) {
			if(allNotedata.get(i).getUserDataBase().getId() == user.getId())
				notedata.add(allNotedata.get(i));
		}
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.getallnotes"), notedata);
	}

	// Update Note
	@Override
	public Response updateNote(String token,int id, NoteDto notedto) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		NoteDataBase noteData = noteRepository.findById(id);
		if(noteData == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		else {
		noteData.setDescription(notedto.getDescription());
		noteData.setTitle(notedto.getTitle());
		noteRepository.save(noteData);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.update"), token);
		}
	}

	// Delete Note
	@Override
	public Response deleteNote(String token, int id) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		if(noteRepository.findById(id) == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		else {
		noteRepository.deleteById(id);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.delete"), token);
		}
	}

	// Pin or UnPin Note
	@Override
	public Response pinOrUnpin(String token, int id) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		NoteDataBase notedata = noteRepository.findById(id);
		if (notedata == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		
		if (notedata.isPinOrUnPin() == false) {
			notedata.setPinOrUnPin(true);
			noteRepository.save(notedata);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("note.ispin"), message.Note_Pin);
		} else {
			notedata.setPinOrUnPin(false);
			noteRepository.save(notedata);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("note.isunpin"), message.Note_UnPin);
		}
	}

	// Archive or UnArchive
	@Override
	public Response archiveOrUnarchive(String token, int id) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		NoteDataBase notedata = noteRepository.findById(id);
		if (notedata == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);

		if (notedata.isArchiveOrUnArchive() == false) {
			notedata.setArchiveOrUnArchive(true);
			noteRepository.save(notedata);
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.isarchive"), message.Note_Archive);
		} else {
			notedata.setArchiveOrUnArchive(false);
			noteRepository.save(notedata);
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.isunarchive"), message.Note_UnArchive);
		}
	}

	// Trash or UnTrash
	@Override
	public Response trashOrUntrash(String token, int id) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		NoteDataBase notedata = noteRepository.findById(id);
		if (notedata == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);

		if (notedata.isTrashOrUnTrash() == false) {
			notedata.setTrashOrUnTrash(true);
			noteRepository.save(notedata);
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.istrash"), message.Note_Trash);
		} else {
			notedata.setTrashOrUnTrash(false);
			noteRepository.save(notedata);
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.isuntrash"), message.Note_UnTrash);
		}
	}
}
