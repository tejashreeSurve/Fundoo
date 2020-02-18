package com.bridgelabz.userloginregistration.repository.label;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userloginregistration.model.label.LabelDataBase;

public interface LabelRepository extends JpaRepository<LabelDataBase, Integer> {
	LabelDataBase findById(int id);

	LabelDataBase findByLabelname(String name);
}
