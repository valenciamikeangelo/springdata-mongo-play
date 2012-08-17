package builders;

import java.util.List;

import models.Account;
import models.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import repository.AccountRepository;

public class EventBuilder {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations mongoOps;	
	
	@Autowired
	private AccountRepository repository;
	
	public Event buildEvent(Event event){
		if(event!=null){
			buildOrganizer(event);
			buildAttendees(event);
		}
		return event;
	}
	
	public List<Event> buildEvents(List<Event> events){
		for(Event event:events)
		{
			buildEvent(event);
		}	
		return events;
	}
	
	private void buildAttendees(Event event){
		Query query = new Query(Criteria.where("id").in(event.attendeesIds));
		event.attendees=mongoOps.find(query, Account.class);
	}
	
	private void buildOrganizer(Event event){
		event.organizer=repository.findOne(event.organizerId);
	}
	
}
