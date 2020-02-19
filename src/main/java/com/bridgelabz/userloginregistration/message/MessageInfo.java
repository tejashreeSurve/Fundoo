package com.bridgelabz.userloginregistration.message;

import org.springframework.stereotype.Component;

@Component
public class MessageInfo {
	// messages for user
	public String Invalide_Token = "Invalide Token";

	public String User_Exist = "Please try with other Email-id";

	public String Registration_Done = "Befor Login Please Verify Email-id ";

	public String Login_Done = "Loged-In Successfully";

	public String Invalide_Password = "Please Try with other Password";

	public String User_Not_Exist = "Please try with another Email-id";

	public String Token_Send = "Please verify Token for Forget Password";

	public String User_Not_Verify = "Please first Verify Email-id by token";

	public String Update_Password = "User can login with new Password";

	public String Verify_User = "User can Login Successfully";

	// messages for notes
	public String Note_Not_Exist = "Note does not Exist";
	
	public String Note_UnPin = "You can Pin it again";
	
	public String Note_Pin = "You can UnPin it again";
	
	public String Note_Archive = "You can UnAchive it again";
	
	public String Note_UnArchive = "You can Archive it agin";
	
	public String Note_UnTrash = "You can Trash it again";
	
	public String Note_Trash = "You can UnTrash it again";
	
	public String Note_Label_Not_Exist = "No Note with this Label";
	
	// messages for label
	public String Label_Not_Exist = "No Label present with this Id";
	
	public String Label_Change = "Label Name is been changed";
	
	public String Reminder_isPresent = "Cannot Add Reminder";
	
	public String Reminder_isNotPresent = "Please First add Reminder to Note";
}
