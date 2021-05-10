package resources;

import pojo.AddInvalidUser;
import pojo.AddValidUser;

public class TestDataBuild {
	
	public AddInvalidUser invalidUserPayload(String email) {
		AddInvalidUser invalidUser = new AddInvalidUser();
		invalidUser.setEmail(email); 
	    return invalidUser;		
	}
	
	public AddValidUser validUserPayload(String name, String job) {
		AddValidUser validUser = new AddValidUser();
		validUser.setName(name); 
		validUser.setJob(job);
	    return validUser;		
	}
}
