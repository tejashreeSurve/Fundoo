package com.bridgelabz.userloginregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userloginregistration.dto.note.NoteDto;
import com.bridgelabz.userloginregistration.response.Response;
import com.bridgelabz.userloginregistration.services.note.NoteServiceImp;

@RestController
public class NoteController {

	@Autowired
	NoteServiceImp noteservice;

	// Create New Note
	@PostMapping("/notes/createnote")
	public ResponseEntity<Response> createNote(@RequestHeader String token, @RequestBody NoteDto notedto) {
		Response response = noteservice.createNote(token, notedto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// Show All Note
	@GetMapping("/notes/getallnotes")
	public ResponseEntity<Response> getallNotes(@RequestHeader String token) {
		Response response = noteservice.getAllNotes(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// Update Note
	@PutMapping("/notes/updatenote/{id}")
	public ResponseEntity<Response> updateNote(@RequestHeader String token,@PathVariable int id, @RequestBody NoteDto notedto) {
		Response response = noteservice.updateNote(token, id,notedto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// Delete Note
	@DeleteMapping("/notes/deletenote/{id}")
	public ResponseEntity<Response> deleteNote(@RequestHeader String token, @PathVariable int id) {
		Response response = noteservice.deleteNote(token, id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
