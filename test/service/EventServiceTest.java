package service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import beans.AccountService;
import beans.EventService;

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
	public void testCreateAccountEvent(){
		
	}
}
