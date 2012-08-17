package beans;

import java.util.Date;
import java.util.List;

import models.Account;
import models.Event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;

import builders.EventBuilder;

import repository.EventRepository;

public class EventService {
	
	private static final Log log = LogFactory.getLog(AccountService.class);
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations eventOps;	

	@Autowired
	private EventRepository repository;
	
	@Autowired
	private EventBuilder eventBuilder;
	
	public Event createEventForAccount(Account eventCreator,String title,String description,Date eventDate){
		Event event = new Event(eventCreator.id, title, description, eventDate);
		return repository.save(event);
	}
	
	public Event retriveById(ObjectId id) {
		return eventBuilder.buildEvent(repository.findOne(id));
	}
	
	public List<Event> retriveByEventCreator(Account eventCreator) {
		return eventBuilder.buildEvents(repository.findByOrganizer(eventCreator.id));
	}
	
	public Event addAttendees(Event event,List<Account> attendees){
		for(Account attendee:attendees){
			if(!isAttendee(event,attendee)){
				event.attendeesIds.add(attendee.id);
			}
		}
		return repository.save(event);
	}
	
	public boolean isAttendee(Event event,Account attendee){
		return event.attendeesIds.contains(attendee.id);
	}
}
