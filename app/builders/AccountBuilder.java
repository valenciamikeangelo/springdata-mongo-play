package builders;

import models.Account;
import models.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import beans.PostService;

public class AccountBuilder {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations mongoOps;	
	
	public Account buildAccount(Account account){
		if(account!=null){
			buildPosts(account);
			buildCollegues(account);
			buildParticipatedPosts(account);
		}
		return account;
	}
		
	private void buildPosts(Account account){
		account.posts=postService.getPostByAuthor(account);
	}
	
	private void buildCollegues(Account account){
		Query query = new Query(Criteria.where("id").in(account.colleaguesIds));
		account.colleagues=mongoOps.find(query, Account.class);
	}
	
	private void buildParticipatedPosts(Account account){
		Query query = new Query(Criteria.where("id").in(account.participatedPostIds));
		account.participatedPosts=mongoOps.find(query, Post.class);
	}

}
