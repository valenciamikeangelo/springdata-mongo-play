package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import models.Account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import beans.AccountService;
import exceptions.AccountCreateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;
	
	@Test
	public void testBeansLoaded() {
		assertNotNull(accountService);
	}


	@Test
	public void testCRUDAccount() throws AccountCreateException{
		Account account = new Account("test@email.com", "test");
		//Test insert account
		accountService.insertAccount(account);
		assertNotNull(account.id);
		//Test search account
		Account paccount=accountService.retriveById(account.id);
		assertEquals(account.email, paccount.email);
		//Test update account
		paccount.name="new name";
		accountService.updateAccount(paccount);
		paccount=accountService.retriveById(account.id);
		assertEquals("new name", paccount.name);
		//Test delete account
		accountService.deleteAccount(account);
		account=accountService.retriveById(account.id);
		assertNull(account);
	}
	
	
	@Test
	public void testSearchByEmail() throws AccountCreateException{
		Account account = new Account("test@email.com", "test");
		//Test insert account
		accountService.insertAccount(account);
		assertNotNull(account.id);
		//Test search account
		Account paccount=accountService.retriveByEmail(account.email);
		assertEquals(account.email, paccount.email);
		accountService.deleteAccount(account);
		account=accountService.retriveByEmail(account.email);
		assertNull(account);
	}
	
	
	@Test(expected = AccountCreateException.class)
	public void testDuplicateInsert() throws AccountCreateException  {
		Account account = new Account("test@email.com", "test");
		accountService.insertAccount(account);
		Account paccount = accountService.retriveByEmail(account.email);
		assertNotNull(paccount);
		assertEquals("test@email.com", paccount.email);
		Account account2 = new Account("test@email.com", "test");
		accountService.insertAccount(account2);
	}
	
	@Test
	public void dropAccountDocument(){
	//	accountService.getAccountOps().dropCollection(Account.class);
	}
}
