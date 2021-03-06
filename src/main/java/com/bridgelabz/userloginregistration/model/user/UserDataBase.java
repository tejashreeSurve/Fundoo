package com.bridgelabz.userloginregistration.model.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.bridgelabz.userloginregistration.model.note.NoteDataBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Tejashree Surve 
 * Purpose : This is Data Access Object class which is
 *         connect to DataBase Layer.
 */
@Component
@Entity
@Table(name = "loginregistrationdetail")
public class UserDataBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	private String firstname;
	private String lastname;
	private String birthdate;
	private String phonenumber;
	private String city;
	private String email;
	private String userpassword;
	private boolean isValidate;
	
	
	@OneToMany(mappedBy = "userDataBase")
	private List<NoteDataBase> noteDataBase;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public boolean getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

	
	public List<NoteDataBase> getNoteDataBase() {
		return noteDataBase;
	}

	public void setNoteDataBase(List<NoteDataBase> noteDataBase) {
		this.noteDataBase = noteDataBase;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}
}
