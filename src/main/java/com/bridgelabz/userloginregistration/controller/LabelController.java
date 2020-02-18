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
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userloginregistration.dto.label.ChangeLabelDto;
import com.bridgelabz.userloginregistration.dto.label.LabelDto;
import com.bridgelabz.userloginregistration.dto.note.NoteDto;
import com.bridgelabz.userloginregistration.response.Response;
import com.bridgelabz.userloginregistration.services.label.LabelServiceImp;

@RestController
public class LabelController {
	@Autowired
	LabelServiceImp labelservice;
	
		// Create New Label
		@PostMapping("/label/createlabel/{id}")
		public ResponseEntity<Response> createLabel(@RequestHeader String token,@PathVariable int id ,@RequestBody LabelDto labeldto) {
			Response response = labelservice.addLabel(token,id , labeldto);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		// Show All label
		@GetMapping("/label/getalllabel")
		public ResponseEntity<Response> getallLabel(@RequestHeader String token) {
			Response response = labelservice.getAlllabel(token);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		// Update label
		@PutMapping("/label/updatelabel/{id}")
		public ResponseEntity<Response> updateLabel(@RequestHeader String token,@PathVariable int id, @RequestBody LabelDto labeldto) {
			Response response = labelservice.updateLabel(token,id, labeldto);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		// Delete label
		@DeleteMapping("/label/deletelabel/{id}")
		public ResponseEntity<Response> deleteLable(@RequestHeader String token, @PathVariable int id) {
			Response response = labelservice.deleteLabel(token, id);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
		// Show Note By Label ID
		@GetMapping("/label/getNoteByLabelId/{id}")
		public ResponseEntity<Response> getNoteByLabelID(@PathVariable int id) {
			Response response = labelservice.getNoteByLabelId(id);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
		// Show All Note By Label Name
		@GetMapping("/label/getNoteByLabelName")
		public ResponseEntity<Response> getNoteByLabelID(@RequestBody LabelDto labeldto) {
			Response response = labelservice.getNoteByLabelName(labeldto);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
		// Change Label
		@PutMapping("/label/changelabel")
		public ResponseEntity<Response> changeLabel(ChangeLabelDto changelabel) {
			Response response = labelservice.changeLabel(changelabel);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
}
