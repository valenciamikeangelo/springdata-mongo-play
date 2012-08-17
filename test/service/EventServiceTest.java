package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.Account;
import models.Event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import beans.AccountService;
import beans.EventService;
import exceptions.AccountCreateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class EventServiceTest {

	
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private EventService eventService;
	
	@Test
	public void testBeansLoaded() {
		assertNotNull(accountService);
		assertNotNull(eventService);
	}
	
	
	@Test
	public void testCreateAccountEvent() throws AccountCreateException{
		accountService.getAccountOps().dropCollection(Account.class);
		Account eventCreator = new Account("eventCreator@test.com","eventCreator");
		eventCreator=accountService.insertAccount(eventCreator);
		String title ="TITLE2";
		String description ="CONTENT2";
		Date eventDate = Calendar.getInstance().getTime();
		Event event=eventService.createEventForAccount(eventCreator, title, description, eventDate);
		assertNotNull(event);
		assertNotNull(event.id);
		assertEquals(0, event.attendeesIds.size());
		Event eventById=eventService.retriveById(event.id);
		assertNotNull(eventById);
		assertNotNull(eventById.id);
		assertEquals(0, eventById.attendeesIds.size());
		assertNotNull(eventById.organizerId);
		assertNotNull(eventById.organizer);
		Event eventByEventCreator=eventService.retriveByEventCreator(eventCreator).get(0);
		assertNotNull(eventByEventCreator);
		assertNotNull(eventByEventCreator.id);
		assertEquals(0, eventByEventCreator.attendeesIds.size());
		assertNotNull(eventByEventCreator.organizerId);
		assertNotNull(eventByEventCreator.organizer);
		assertEquals(eventCreator, eventByEventCreator.organizer);
		List<Account> attendees= new ArrayList<Account>();
		Account attendee1 = new Account("attendee1@test.com","attendee1");
		attendee1=accountService.insertAccount(attendee1);
		attendees.add(attendee1);
		Event eventWithAttendees=eventService.addAttendees(eventByEventCreator,attendees);
		eventWithAttendees=eventService.retriveByEventCreator(eventCreator).get(0);
		assertNotNull(eventWithAttendees);
		assertEquals(1, eventWithAttendees.attendeesIds.size());
		assertEquals(1, eventWithAttendees.attendees.size());
	}
}
