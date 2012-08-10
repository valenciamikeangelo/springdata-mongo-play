package beans;

import models.Account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;

import exceptions.AccountCreateException;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class AccountService {

	private static final Log log = LogFactory.getLog(AccountService.class);
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations accountOps;	

	public void insertAccount(Account account) throws AccountCreateException {
		Account paccount =retriveByEmail(account.email);
		if(paccount!=null){
			throw new AccountCreateException("Email already Exist!");
		}
		accountOps.insert(account);
		log.info("Insert: " + account);
	}

	public Account retriveById(String id) {
		return accountOps.findById(id, Account.class);
	}
	
	public Account retriveByEmail(String email) {
		return accountOps.findOne(query(where("email").is(email)),Account.class);
	}
	
	public void updateAccount(Account account){
		accountOps.save(account);    
	}
	
	public void deleteAccount(Account account){
		accountOps.remove(account);
		log.info("Delete: " + account);
	}

	public MongoOperations getAccountOps() {
		return accountOps;
	}
}
