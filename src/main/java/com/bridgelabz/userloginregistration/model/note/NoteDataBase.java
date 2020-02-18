package com.bridgelabz.userloginregistration.model.note;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.bridgelabz.userloginregistration.model.label.LabelDataBase;
import com.bridgelabz.userloginregistration.model.user.UserDataBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
@Table(name = "notedatabase")
@JsonIgnoreProperties({"userDataBase","labelList"})
public class NoteDataBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NonNull
	private String title;
	@NonNull
	private String description;
	@NonNull
	private boolean archiveOrUnArchive;
	@NonNull
	private boolean pinOrUnPin;
	@NonNull
	private boolean trashOrUnTrash;
	@ManyToOne
	@JoinColumn(name = "user_id" ,nullable = false)
	private UserDataBase userDataBase;
	@ManyToMany
	private List<LabelDataBase> labelList = new ArrayList<LabelDataBase>();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isArchiveOrUnArchive() {
		return archiveOrUnArchive;
	}

	public void setArchiveOrUnArchive(boolean archiveOrUnArchive) {
		this.archiveOrUnArchive = archiveOrUnArchive;
	}

	public boolean isPinOrUnPin() {
		return pinOrUnPin;
	}

	public void setPinOrUnPin(boolean pinOrUnPin) {
		this.pinOrUnPin = pinOrUnPin;
	}

	public boolean isTrashOrUnTrash() {
		return trashOrUnTrash;
	}

	public void setTrashOrUnTrash(boolean trashOrUnTrash) {
		this.trashOrUnTrash = trashOrUnTrash;
	}

	public UserDataBase getUserDataBase() {
		return userDataBase;
	}

	public void setUserDataBase(UserDataBase userDataBase) {
		this.userDataBase = userDataBase;
	}

	public List<LabelDataBase> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<LabelDataBase> labelList) {
		this.labelList = labelList;
	}
}
