package com.bridgelabz.userloginregistration.repository.reminder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.userloginregistration.model.reminder.ReminderDataBase;

public interface ReminderRepository extends JpaRepository<ReminderDataBase, Integer> {

}
