package com.bridgelabz.userloginregistration.repository.note;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userloginregistration.model.note.NoteDataBase;

public interface NoteRepository extends JpaRepository<NoteDataBase, Integer> {
	NoteDataBase findByTitle(String title);

	NoteDataBase findById(int id);
}
