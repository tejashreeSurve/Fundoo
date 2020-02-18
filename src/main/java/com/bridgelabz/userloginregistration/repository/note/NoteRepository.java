package com.bridgelabz.userloginregistration.repository.note;

import org.dom4j.util.UserDataAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userloginregistration.model.label.LabelDataBase;
import com.bridgelabz.userloginregistration.model.note.NoteDataBase;
import com.bridgelabz.userloginregistration.model.user.UserDataBase;

public interface NoteRepository extends JpaRepository<NoteDataBase, Integer> {
	NoteDataBase findByTitle(String title);

	NoteDataBase findById(int id);
	
	NoteDataBase findByUserDataBase(UserDataBase userdata);
	
}
