package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;


public class Event {

	public ObjectId id;
	public String title;
	public String description;
	public Date eventDate;
	public ObjectId organizerId;
	public List<ObjectId>attendeesIds;
	 
	
	//non-persisted attributes,data is derived from a builder
	public Account organizer;
	public List<Account> attendees;
	
		
	
	
	public Event(ObjectId organizerId, String title, String description, Date eventDate){
		this.organizerId=organizerId;
		this.title=title;
		this.description=description;
		this.eventDate=eventDate;
		this.attendeesIds=new ArrayList<ObjectId>();
		this.attendees=new ArrayList<Account>();
	}
	
}
