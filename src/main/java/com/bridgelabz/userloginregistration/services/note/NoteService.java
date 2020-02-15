package com.bridgelabz.userloginregistration.services.note;

import com.bridgelabz.userloginregistration.dto.note.NoteDto;
import com.bridgelabz.userloginregistration.response.Response;

public interface NoteService {

	Response createNote(String token,NoteDto notedto);
	
	Response getAllNotes(String token);
	
	Response updateNote(String token,NoteDto notedto);
	
	Response deleteNote(String token,int id);
}
