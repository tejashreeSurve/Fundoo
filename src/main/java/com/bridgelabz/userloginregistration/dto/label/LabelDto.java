package com.bridgelabz.userloginregistration.dto.label;

import org.springframework.lang.NonNull;

/**
 * @author Tejashree Surve
 * Purpose : This is Data Transfer Object class for label Api. 
 */
public class LabelDto {
	@NonNull
	private String labelname;

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}
}
