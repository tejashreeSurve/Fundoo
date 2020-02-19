package com.bridgelabz.userloginregistration.dto.reminder;

import javax.validation.constraints.Pattern;

import org.springframework.lang.NonNull;

public class ReminderDto {
	@NonNull
	private String date;
	@NonNull
	@Pattern(regexp = "HH:MM")
	private String time;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
