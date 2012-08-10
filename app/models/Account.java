package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {

	public String id;
	public String email;
	public String name;
	public List<Post> posts;
	public List<String> participatedPosts;
	
	public Account() {

	}

	public Account(String email, String name) {
		this.email = email;
		this.name = name;
		participatedPosts= new ArrayList<String>();
	}
	
	
	 @Override
	  public String toString() {
	    return "Account [id=" + id + ", name=" + name + ", email=" + email + "]";
	  }
}
