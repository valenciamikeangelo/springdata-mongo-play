package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


public class Post {

	public ObjectId id;
	public String title;
	public Date postedAt;
	public String content;
	public ObjectId authorId;
	public List<Comment> comments;

	//non-persisted attributes,data is derived from a builder
	public Account author;
	
	public Post() {

	}

	public Post(ObjectId authorId, String title, String content) {
		this.authorId = authorId;
		this.title = title;
		this.content = content;
		this.postedAt = new Date();
		this.comments= new ArrayList<Comment>();
	}

}
