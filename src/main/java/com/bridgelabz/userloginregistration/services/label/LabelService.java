package com.bridgelabz.userloginregistration.services.label;

import com.bridgelabz.userloginregistration.dto.label.ChangeLabelDto;
import com.bridgelabz.userloginregistration.dto.label.LabelDto;
import com.bridgelabz.userloginregistration.model.label.LabelDataBase;
import com.bridgelabz.userloginregistration.response.Response;

/**
 * @author Tejashree Surve
 * Purpose : This is LabelService Interface which contains every method of services class.
 */
public interface LabelService {
	// add label
	Response addLabel(String token, int id,LabelDto labeldto);
	
	// get all label 
	Response getAlllabel(String token);
	
	// update label
	Response updateLabel(String token,int id, LabelDto labeldto);
	
	// delete label
	Response deleteLabel(String token, int id);
	
	// get label by id
	Response getNoteByLabelId(int id);
	
	// get label by label name
	Response getNoteByLabelName(LabelDto labeldto);
	
	// change label
	Response changeLabel(ChangeLabelDto changelabel);
}
