package com.bridgelabz.userloginregistration.dto.label;

import org.springframework.lang.NonNull;

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
