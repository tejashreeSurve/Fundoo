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

import com.bridgelabz.userloginregistration.dto.reminder.ReminderDto;
import com.bridgelabz.userloginregistration.response.Response;
import com.bridgelabz.userloginregistration.services.reminder.ReminderServiceImp;

@RestController
public class ReminderController {
	@Autowired
	ReminderServiceImp reminderservice;
		// Add reminder to note
		@PostMapping("/reminder/addreminder/{noteid}")
		public ResponseEntity<Response> addReminder(@RequestHeader String token,@PathVariable int noteid, @RequestBody ReminderDto reminderDto) {
			Response response = reminderservice.addReminder(token, noteid, reminderDto);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		// Show reminder
		@GetMapping("/reminder/getreminder")
		public ResponseEntity<Response> getReminder(@RequestHeader String token) {
			Response response = reminderservice.getReminder(token);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		// update reminder
		@PutMapping("/reminder/updatereminder/{noteid}")
		public ResponseEntity<Response> updateReminder(@RequestHeader String token,@PathVariable int noteid, @RequestBody ReminderDto reminderDto) {
			Response response = reminderservice.updateReminder(token, noteid, reminderDto);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		// Delete reminder from note
		@DeleteMapping("/reminder/deletereminder/{noteid}")
		public ResponseEntity<Response> deleteReminder(@RequestHeader String token, @PathVariable int noteid) {
			Response response = reminderservice.deleteReminder(token, noteid);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
		@PutMapping("/reminder/repeatDaily/{noteid}")
		public ResponseEntity<Response> repeatDaily(@RequestHeader String token,@PathVariable int noteid) {
			Response response = reminderservice.repeatDaily(token, noteid);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
		@PutMapping("/reminder/repeatWeekly/{noteid}")
		public ResponseEntity<Response> repeatWeekly(@RequestHeader String token,@PathVariable int noteid) {
			Response response = reminderservice.repeatWeekly(token, noteid);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
		@PutMapping("/reminder/repeatMonthly/{noteid}")
		public ResponseEntity<Response> repeatMonthly(@RequestHeader String token,@PathVariable int noteid) {
			Response response = reminderservice.repeatMonthly(token, noteid);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
		@PutMapping("/reminder/repeatYearly/{noteid}")
		public ResponseEntity<Response> repeatYearly(@RequestHeader String token,@PathVariable int noteid) {
			Response response = reminderservice.repeatYearly(token, noteid);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
		@PutMapping("/reminder/doNotRepeat/{noteid}")
		public ResponseEntity<Response> doNotRepeat(@RequestHeader String token,@PathVariable int noteid) {
			Response response = reminderservice.doNotRepeat(token, noteid);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
}
