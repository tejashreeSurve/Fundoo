package com.bridgelabz.userloginregistration.repository.note;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.userloginregistration.model.note.NoteDataBase;
import com.bridgelabz.userloginregistration.model.user.UserDataBase;

/**
 * @author Tejashree Surve 
 * Purpose : This is NoteRepository Interface which extends inbuilt JpaRepository.
 */
public interface NoteRepository extends JpaRepository<NoteDataBase, Integer> {
	// find data by Note title
	NoteDataBase findByTitle(String title);

	// find data by Note Id
	NoteDataBase findById(int id);

	// find data by user
	NoteDataBase findByUserDataBase(UserDataBase userdata);

}
