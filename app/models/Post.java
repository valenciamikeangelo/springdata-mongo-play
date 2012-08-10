package models;

import java.util.Date;
import java.util.List;

public class Post {

	
	public String id;
	public String title;
	public Date postedAt;
	public String content;
	public Account author;
	public List<Comment> comments;

	public Post() {

	}

	public Post(Account author, String title, String content) {
		this.author = author;
		this.title = title;
		this.content = content;
		this.postedAt = new Date();
	}

}
