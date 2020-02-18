package com.bridgelabz.userloginregistration.services.label;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.userloginregistration.dto.label.ChangeLabelDto;
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
	public Response addLabel(String token, int id, LabelDto labeldto) {
		String email = jwttoken.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		NoteDataBase notedata = noteRepository.findById(id);
		

		if (notedata == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("note.notexist"), message.Note_Not_Exist);
		else {
			LabelDataBase labeldatabase = mapper.map(labeldto, LabelDataBase.class);
			List<NoteDataBase> notelist = labeldatabase.getNoteList();
			notelist.add(notedata);
			labeldatabase.setNoteList(notelist);
			// setting the list of label in NoteDataBase to list
			List<LabelDataBase> labellist = notedata.getLabelList();
			labellist.add(labeldatabase);
			notedata.setLabelList(labellist);

			labelRepository.save(labeldatabase);
			return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
					environment.getProperty("label.create"), token);
		}
	}

	@Override
	public Response getAlllabel(String token) {
		String email = jwttoken.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		List<NoteDataBase> notelist = noteRepository.findAll();
		List<NoteDataBase> notedata = new ArrayList<NoteDataBase>();
		for(int i=0;i<notelist.size();i++)
		{
			if(notelist.get(i).getUserDataBase().getId() == user.getId())
				notedata.add(notelist.get(i));
		}
		List<LabelDataBase> labellist = new ArrayList<LabelDataBase>();
		for(int i=0;i<notedata.size();i++) {
			for(int j=0;j<notedata.get(i).getLabelList().size();j++) {
			labellist.add(notedata.get(i).getLabelList().get(j));
			}
		}
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("label.getAllLabels"), labellist);
	}

	@Override
	public Response updateLabel(String token, int id, LabelDto labeldto) {
		String email = jwttoken.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		LabelDataBase labelData = labelRepository.findById(id);
		if(labelData == null )
			return new Response(Integer.parseInt(environment.getProperty("status.redirect.code")),
					environment.getProperty("label.notexist"), message.Label_Not_Exist);
		labelData.setLabelname(labeldto.getLabelname());
		labelRepository.save(labelData);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("label.update"), token);
	}

	@Override
	public Response deleteLabel(String token, int id) {
		String email = jwttoken.getToken(token);
		UserDataBase user = userRepository.findByEmail(email);
		if (user == null)
			return new Response(Integer.parseInt(environment.getProperty("status.bad.code")),
					environment.getProperty("status.email.notexist"), message.User_Not_Exist);
		LabelDataBase labelData = labelRepository.findById(id);
		if(labelData == null )
			return new Response(Integer.parseInt(environment.getProperty("status.redirect.code")),
					environment.getProperty("label.notexist"), message.Label_Not_Exist);
		labelRepository.deleteById(id);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("label.delete"), token);
	}

	@Override
	public Response getNoteByLabelId(int id) {
		LabelDataBase labelData = labelRepository.findById(id);
		if(labelData == null )
			return new Response(Integer.parseInt(environment.getProperty("status.redirect.code")),
					environment.getProperty("label.notexist"), message.Label_Not_Exist);
		List<NoteDataBase> notelist = noteRepository.findAll();
		List<LabelDataBase> label = new ArrayList<LabelDataBase>();
		for(int i=0;i<notelist.size();i++) {
			for(int j=0;j<notelist.get(i).getLabelList().size();j++) {
				if(notelist.get(i).getLabelList().get(j).getId() == id)
					label.add(notelist.get(i).getLabelList().get(j));
			}
		}
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.getallnotes"), label);
	}

	@Override
	public Response getNoteByLabelName(LabelDto labeldto) {
		List<NoteDataBase> notelist = noteRepository.findAll();
		List<LabelDataBase> label = new ArrayList<LabelDataBase>();
		for(int i=0;i<notelist.size();i++) {
			for(int j=0;j<notelist.get(i).getLabelList().size();j++) {
				if(notelist.get(i).getLabelList().get(j).getLabelname() .equals(labeldto.getLabelname()))
					label.add(notelist.get(i).getLabelList().get(j));
			}
		}
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("note.getallnotes"), label);
	}
	@Override
	public Response changeLabel(ChangeLabelDto changelabel) {
		LabelDataBase labelOldData = labelRepository.findById(changelabel.getPreLabelId());
		LabelDataBase labelChangeData = labelRepository.findById(changelabel.getChangeLabelId());
		System.out.println(labelChangeData);
		labelChangeData.setLabelname(labelOldData.getLabelname());
		labelRepository.save(labelChangeData);
		return new Response(Integer.parseInt(environment.getProperty("status.success.code")),
				environment.getProperty("label.change"), message.Label_Change);
	}
	}
