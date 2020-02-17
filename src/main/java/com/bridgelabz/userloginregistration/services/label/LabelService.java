package com.bridgelabz.userloginregistration.services.label;

import com.bridgelabz.userloginregistration.dto.label.LabelDto;
import com.bridgelabz.userloginregistration.model.label.LabelDataBase;
import com.bridgelabz.userloginregistration.response.Response;

public interface LabelService {
 
	Response addLabel(String token, int id,LabelDto labeldto);
	
	Response getAlllabel(String token);
	
	Response updateLabel(String token,int id, LabelDto labeldto);
	
	Response deleteLabel(String token, int id);
}
