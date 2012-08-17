package beans;

import models.Account;
import models.Post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;

import repository.AccountRepository;
import builders.AccountBuilder;
import exceptions.AccountCreateException;

public class AccountService {

	private static final Log log = LogFactory.getLog(AccountService.class);
	
	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private AccountBuilder accountBuilder;
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations accountOps;	
	
	public Account insertAccount(Account account) throws AccountCreateException {
		if(retriveByEmail(account.email,false)!=null){
			throw new AccountCreateException("Email already Exist!");
		}
		return repository.save(account);
	}
	
	public Account retriveById(ObjectId id, boolean fullData) {
		Account account=repository.findOne(id);
		if(fullData && account!=null){
			return accountBuilder.buildAccount(account);
		}
		return account;
	}
	
	public Account retriveByEmail(String email,boolean fullData) {
		Account account=repository.findByEmail(email);
		if(fullData && account!=null){
			accountBuilder.buildAccount(account);
		}
		return account;
	}
	
	public Account updateAccount(Account account){
		return  repository.save(account);    
	}
	
	public void deleteAccount(Account account){
		repository.delete(account);
		log.info("Delete: " + account);
	}
	
	public Account addColleague(Account owner,Account colleague){
		if(colleague!=null && !isColleague(owner,colleague)){
			Account newColleague=retriveByEmail(colleague.email,false);
			owner.colleaguesIds.add(newColleague.id);
			owner=updateAccount(owner);
		}
		return owner;
	}
	
	public Account addParticipatedPost(Account owner, Post post){
		if(post!=null && !hasAlreadyParticipated(owner,post)){
			owner.participatedPostIds.add(post.id);
			owner=updateAccount(owner);
		}
		return owner;
	}
	
	public boolean isColleague(Account owner,Account colleague){
		return owner.colleaguesIds.contains(colleague.id);
	}
			
	public boolean hasAlreadyParticipated(Account owner, Post post){
		return owner.participatedPostIds.contains(post.id);
	}
	
	public MongoOperations getAccountOps() {
		return accountOps;
	}

}
