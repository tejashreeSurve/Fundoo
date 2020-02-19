package com.bridgelabz.userloginregistration.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userloginregistration.model.user.UserDataBase;

/**
 * @author Tejashree Surve
 * Purpose : This is UserRepository which access the data from DataBase.
 */
public interface UserRepository extends JpaRepository<UserDataBase, Integer> {
	// find data by user email
	UserDataBase findByEmail(String email);
}
