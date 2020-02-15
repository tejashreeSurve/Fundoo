package com.bridgelabz.userloginregistration.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userloginregistration.model.user.UserDataBase;

/**
 * @author Tejashree Surve
 * Purpose : This is Repository which access the data from DataBase.
 */
public interface UserRepository extends JpaRepository<UserDataBase, Integer> {
	UserDataBase findByEmail(String email);
}