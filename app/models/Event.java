package models;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;


public class Event {

	public ObjectId id;
	public String title;
	public String description;
	public Date date;
	public String groupOwnerId;
	public String organizerId;
	public Account organizer;
	public List<Account> attendees; 
	public Event(){
		
	}
	
	public Event(Account eventCreator,String title,String description,Date eventDate){
		
	}
	
}
