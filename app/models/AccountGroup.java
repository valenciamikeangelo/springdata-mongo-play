package models;

import java.util.List;

public class AccountGroup {
	
	public String id;
	public Account creator;
	public List<Account> members;
	public List<Post> posts;
	public List<Event> events;
	
	public AccountGroup(){
		
	}

}
