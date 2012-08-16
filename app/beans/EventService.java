package beans;

import java.util.Date;

import models.Account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;

public class EventService {
	
	private static final Log log = LogFactory.getLog(AccountService.class);
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations eventOps;	

	
	
	public void createEventForAccount(Account eventCreator,String title,String description,Date eventDate){
		
	}
	
	
	
}
