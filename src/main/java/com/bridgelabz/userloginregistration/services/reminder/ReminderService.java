package com.bridgelabz.userloginregistration.services.reminder;

import com.bridgelabz.userloginregistration.dto.reminder.ReminderDto;
import com.bridgelabz.userloginregistration.response.Response;

public interface ReminderService {

Response addReminder(String token,int noteid,ReminderDto reminderdto);

Response getReminder(String token);

Response updateReminder(String token ,int noteid,ReminderDto reminderDto);

Response deleteReminder(String token , int noteid);

Response repeatDaily(String token , int noteid);

Response repeatWeekly(String token , int noteid);

Response repeatMonthly(String token , int noteid);

Response repeatYearly(String token , int noteid);

Response doNotRepeat(String token , int noteid);
}
