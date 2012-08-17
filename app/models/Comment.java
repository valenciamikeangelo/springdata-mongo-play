package models;

import java.util.Date;

import org.bson.types.ObjectId;


public class Comment{
	
	public ObjectId id;
	public ObjectId commenterId;
    public Date postedAt;
    public String content;
    
    //non-persisted attributes,data is derived from a builder
    public Account commenter;
    
    public Comment(){
    	
    }
    
    public Comment(ObjectId commenterId, String content) {
        this.commenterId = commenterId;
        this.content = content;
        this.postedAt = new Date();
    }
 
}
