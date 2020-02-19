package com.bridgelabz.userloginregistration.repository.label;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userloginregistration.model.label.LabelDataBase;

/**
 * @author Tejashree Surve 
 * Purpose : This is LabelRepository Interface which extends inbuilt JpaRepository.
 */
public interface LabelRepository extends JpaRepository<LabelDataBase, Integer> {
	// find data by label id
	LabelDataBase findById(int id);

	// find data by label name
	LabelDataBase findByLabelname(String name);
}
