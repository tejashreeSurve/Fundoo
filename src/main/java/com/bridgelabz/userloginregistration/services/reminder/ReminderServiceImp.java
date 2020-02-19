package com.bridgelabz.userloginregistration.services.reminder;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.userloginregistration.dto.reminder.ReminderDto;
import com.bridgelabz.userloginregistration.message.MessageInfo;
import com.bridgelabz.userloginregistration.model.note.NoteDataBase;
import com.bridgelabz.userloginregistration.model.reminder.ReminderDataBase;
import com.bridgelabz.userloginregistration.model.user.UserDataBase;
import com.bridgelabz.userloginregistration.repository.note.NoteRepository;
import com.bridgelabz.userloginregistration.repository.reminder.ReminderRepository;
import com.bridgelabz.userloginregistration.repository.user.UserRepository;
import com.bridgelabz.userloginregistration.response.Response;
import com.bridgelabz.userloginregistration.utility.JwtToken;

@Component
@Service
@PropertySource("message.properties")
public class ReminderServiceImp implements ReminderService {

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
	ReminderRepository reminderRepository;

	@Override
	public Response addReminder(String token, int noteid, ReminderDto reminderDto) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);

		List<NoteDataBase> allNotedata = noteRepository.findAll();
		List<NoteDataBase> listOfNotes = allNotedata.stream()
				.filter(userdata -> userdata.getUserDataBase().getId() == user.getId()).collect(Collectors.toList());

		for(int i=0;i<listOfNotes.size();i++) {
		if (listOfNotes.get(i).getId() != noteid)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		}

		NoteDataBase noteData = noteRepository.findById(noteid);
		if (noteData.getReminderDataBase() == null) {
			ReminderDataBase reminderData = mapper.map(reminderDto, ReminderDataBase.class);
			reminderData.setNoteDataBase(noteData);
			reminderRepository.save(reminderData);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.add"), token);
		} else {
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.ispresent"), message.Reminder_isPresent);
		}
	}

	@Override
	public Response getReminder(String token) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		List<NoteDataBase> allNotedata = noteRepository.findAll();
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		if (allNotedata == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		// this stream filter those notes which contains user id is equal to given token
		List<NoteDataBase> listOfNotes = allNotedata.stream()
				.filter(userdata -> userdata.getUserDataBase().getId() == user.getId()).collect(Collectors.toList());
		List<NoteDataBase> notesReminder = listOfNotes.stream()
				.filter(reminder -> reminder.getReminderDataBase() != null).collect(Collectors.toList());
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("reminder.show"), notesReminder);
	}

	@Override
	public Response updateReminder(String token, int noteid, ReminderDto reminderDto) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		List<NoteDataBase> allNotedata = noteRepository.findAll();
		List<NoteDataBase> listOfNotes = allNotedata.stream()
				.filter(userdata -> userdata.getUserDataBase().getId() == user.getId()).collect(Collectors.toList());

		if (listOfNotes == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		NoteDataBase noteData = noteRepository.findById(noteid);
		if (noteData.getReminderDataBase() != null) {
			ReminderDataBase reminderData = noteData.getReminderDataBase();
			reminderData.setDate(reminderDto.getDate());
			reminderData.setTime(reminderDto.getTime());
			reminderRepository.save(reminderData);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.update"), token);
		} else {
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.isnotpresent"), message.Reminder_isNotPresent);
		}
	}

	@Override
	public Response deleteReminder(String token, int noteid) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		List<NoteDataBase> allNotedata = noteRepository.findAll();

		List<NoteDataBase> listOfNotes = allNotedata.stream()
				.filter(userdata -> userdata.getUserDataBase().getId() == user.getId()).collect(Collectors.toList());

		if (listOfNotes == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		NoteDataBase noteData = noteRepository.findById(noteid);
		if (noteData.getReminderDataBase() != null) {
			ReminderDataBase reminderData = noteData.getReminderDataBase();
			reminderRepository.deleteById(reminderData.getId());
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.delete"), token);
		} else {
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.isnotpresent"), message.Reminder_isNotPresent);
		}
	}

	@Override
	public Response repeatDaily(String token, int noteid) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		List<NoteDataBase> allNotedata = noteRepository.findAll();

		List<NoteDataBase> listOfNotes = allNotedata.stream()
				.filter(userdata -> userdata.getUserDataBase().getId() == user.getId()).collect(Collectors.toList());

		if (listOfNotes == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		NoteDataBase noteData = noteRepository.findById(noteid);

		ReminderDataBase reminderData = noteData.getReminderDataBase();
		if (reminderData == null)
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.isnotpresent"), message.Reminder_isNotPresent);

		if ((reminderData.isMonthly() == false) && (reminderData.isWeekly() == false)
				&& (reminderData.isYearly() == false) && (reminderData.isDoNotRepeat() == false)) {
			reminderData.setDaily(true);
			reminderRepository.save(reminderData);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.set.daily"), message.Note_Not_Exist);
		} else {

			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("reminder.notset"), message.Note_Not_Exist);
		}
	}

	@Override
	public Response repeatWeekly(String token, int noteid) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);

		List<NoteDataBase> allNotedata = noteRepository.findAll();

		List<NoteDataBase> listOfNotes = allNotedata.stream()
				.filter(userdata -> userdata.getUserDataBase().getId() == user.getId()).collect(Collectors.toList());

		if (listOfNotes == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		NoteDataBase noteData = noteRepository.findById(noteid);

		ReminderDataBase reminderData = noteData.getReminderDataBase();
		if (reminderData == null)
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.isnotpresent"), message.Reminder_isNotPresent);

		if ((reminderData.isMonthly() == false) && (reminderData.isDaily() == false)
				&& (reminderData.isYearly() == false) && (reminderData.isDoNotRepeat() == false)) {
			reminderData.setWeekly(true);
			reminderRepository.save(reminderData);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.set.weekly"), token);
		} else {

			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("reminder.notset"), token);
		}

	}

	@Override
	public Response repeatMonthly(String token, int noteid) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);

		List<NoteDataBase> allNotedata = noteRepository.findAll();
		List<NoteDataBase> listOfNotes = allNotedata.stream()
				.filter(userdata -> userdata.getUserDataBase().getId() == user.getId()).collect(Collectors.toList());

		if (listOfNotes == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		NoteDataBase noteData = noteRepository.findById(noteid);
		ReminderDataBase reminderData = noteData.getReminderDataBase();
		
		if (reminderData == null)
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.isnotpresent"), message.Reminder_isNotPresent);
		
		if ((reminderData.isDaily() == false) && (reminderData.isWeekly() == false)
				&& (reminderData.isYearly() == false) && (reminderData.isDoNotRepeat() == false)) {
			reminderData.setMonthly(true);
			reminderRepository.save(reminderData);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.set.monthly"), token);
		} else {

			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("reminder.notset"), token);
		}
	}

	@Override
	public Response repeatYearly(String token, int noteid) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);

		List<NoteDataBase> allNotedata = noteRepository.findAll();
		List<NoteDataBase> listOfNotes = allNotedata.stream()
				.filter(userdata -> userdata.getUserDataBase().getId() == user.getId()).collect(Collectors.toList());

		if (listOfNotes == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		NoteDataBase noteData = noteRepository.findById(noteid);
		ReminderDataBase reminderData = noteData.getReminderDataBase();
		
		if (reminderData == null)
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.isnotpresent"), message.Reminder_isNotPresent);
		if ((reminderData.isMonthly() == false) && (reminderData.isWeekly() == false)
				&& (reminderData.isDaily() == false) && (reminderData.isDoNotRepeat() == false)) {
			reminderData.setYearly(true);
			reminderRepository.save(reminderData);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.set.yearly"), token);
		} else {

			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("reminder.notset"), token);
		}
	}

	@Override
	public Response doNotRepeat(String token, int noteid) {
		String email = jwtOperation.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		List<NoteDataBase> allNotedata = noteRepository.findAll();
		List<NoteDataBase> listOfNotes = allNotedata.stream()
				.filter(userdata -> userdata.getUserDataBase().getId() == user.getId()).collect(Collectors.toList());
		
		if (listOfNotes == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		
		NoteDataBase noteData = noteRepository.findById(noteid);
		ReminderDataBase reminderData = noteData.getReminderDataBase();
		
		if(reminderData == null)
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.isnotpresent"), message.Reminder_isNotPresent);
		if ((reminderData.isMonthly() == false) && (reminderData.isWeekly() == false)
				&& (reminderData.isYearly() == false) && (reminderData.isDaily() == false)) {
			reminderData.setDoNotRepeat(true);
			reminderRepository.save(reminderData);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("reminder.set.DoNotRepeat"), token);
		} else {

			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("reminder.notset"), token);
		}
	}
}
