package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;


public class Account {

	public ObjectId id;
	public String email;
	public String name;
	public List<ObjectId> colleaguesIds;
	public List<ObjectId> participatedPostIds;
	
	
	//non-persisted attributes,data is derived from a builder
	public List<Post> posts;
	public List<Post> participatedPosts;
	public List<Account> colleagues;
	
		
	
	public Account() {

	}

	public Account(String email, String name) {
		this.email = email;
		this.name = name;
		this.colleaguesIds= new ArrayList<ObjectId>();
		this.colleagues=new ArrayList<Account>();
		this.participatedPostIds=new ArrayList<ObjectId>();
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Account guest = (Account) obj;
        return  this.email.equals(guest.email);
   }
	
	 @Override
	  public String toString() {
	    return "Account [id=" + id + ", name=" + name + ", email=" + email + "]";
	  }
}
