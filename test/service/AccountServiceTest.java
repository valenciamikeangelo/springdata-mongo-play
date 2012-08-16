package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.*;
import models.Account;
import models.Post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import beans.AccountService;
import beans.PostService;
import exceptions.AccountCreateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PostService postService;
	
	@Test
	public void testBeansLoaded() {
		assertNotNull(accountService);
	}


	@Test
	public void testCRUDAccount() throws AccountCreateException{
		accountService.getAccountOps().dropCollection(Account.class);
		Account account = new Account("test@email.com", "test");
		//Test insert account
		accountService.insertAccount(account);
		assertNotNull(account.id);
		//Test search account
		Account paccount=accountService.retriveById(account.id,false);
		assertEquals(account.email, paccount.email);
		//Test update account
		paccount.name="new name";
		accountService.updateAccount(paccount);
		paccount=accountService.retriveByEmail(account.email,false);
		assertEquals("new name", paccount.name);
		//Test delete account
		accountService.deleteAccount(account);
		account=accountService.retriveById(account.id,false);
		assertNull(account);
	}
	
	
	@Test
	public void testSearchByEmail() throws AccountCreateException{
		accountService.getAccountOps().dropCollection(Account.class);
		Account account = new Account("test@email.com", "test");
		//Test insert account
		accountService.insertAccount(account);
		assertNotNull(account.id);
		//Test search account
		Account paccount=accountService.retriveByEmail(account.email,false);
		assertEquals(account.email, paccount.email);
		accountService.deleteAccount(account);
		account=accountService.retriveByEmail(account.email,false);
		assertNull(account);
	}
	
	
	@Test(expected = AccountCreateException.class)
	public void testDuplicateInsert() throws AccountCreateException  {
		Account account = new Account("test@email.com", "test");
		accountService.insertAccount(account);
		Account paccount = accountService.retriveByEmail(account.email,false);
		assertNotNull(paccount);
		assertEquals("test@email.com", paccount.email);
		Account account2 = new Account("test@email.com", "test");
		accountService.insertAccount(account2);
	}
	
	
	@Test
	public void testCreatePost() throws AccountCreateException{
		accountService.getAccountOps().dropCollection(Account.class);
		postService.getPostOps().dropCollection(Post.class);
		Account account = new Account("test@email.com", "test");
		Account saccount=accountService.retriveByEmail(account.email,false);
		if(saccount==null){
			accountService.insertAccount(account);
		}
		String title ="TITLE1";
		String content ="CONTENT1";
		postService.createPost(account, title, content);
		Account paccount = accountService.retriveByEmail(account.email,true);
		assertNotNull(paccount);
		assertEquals(1, paccount.posts.size());
	}
	
	@Test
	public void testAddColleague() throws AccountCreateException{
	Account owner = new Account("test_OWNER@email.com", "OWNER");
	accountService.insertAccount(owner);
	assertNotNull(owner.id);
	Account colleague = new Account("test_COLLEAGUE@email.com", "COLLEAGUE");
	accountService.insertAccount(colleague);
	assertNotNull(colleague.id);
	assertFalse(accountService.isColleague(owner, colleague));
	accountService.addColleague(owner, colleague);
	owner=accountService.retriveByEmail(owner.email, true);
	assertTrue(accountService.isColleague(owner, colleague));
	assertEquals(1,owner.colleagues.size());
	Account colleague2 = new Account("test_COLLEAGUE2@email.com", "COLLEAGUE2");
	accountService.insertAccount(colleague2);
	assertNotNull(colleague2.id);
	assertFalse(accountService.isColleague(owner, colleague2));
	accountService.addColleague(owner, colleague2);
	owner=accountService.retriveByEmail(owner.email, true);
	assertTrue(accountService.isColleague(owner, colleague));
	assertEquals(2,owner.colleagues.size());
	}
	
	@Test
	public void dropData(){
	accountService.getAccountOps().dropCollection(Account.class);
	postService.getPostOps().dropCollection(Post.class);
	}
}
