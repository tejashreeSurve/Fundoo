package com.bridgelabz.userloginregistration.model.label;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.bridgelabz.userloginregistration.model.note.NoteDataBase;
@Component
@Entity
@Table(name = "labeldatabase")
public class LabelDataBase {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String labelname;
	@ManyToMany
	//@JoinTable(name ="notelabel",joinColumns = @JoinColumn(referencedColumnName = "id"))
	private List<NoteDataBase> noteList = new  ArrayList<NoteDataBase>();
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public List<NoteDataBase> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<NoteDataBase> noteList) {
		this.noteList = noteList;
	}

}
