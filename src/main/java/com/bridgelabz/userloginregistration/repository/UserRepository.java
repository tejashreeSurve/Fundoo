package com.bridgelabz.userloginregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userloginregistration.model.UserDataBase;

public interface UserRepository extends JpaRepository<UserDataBase, Integer>{
 UserDataBase findByEmail(String email);
}
