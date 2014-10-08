package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import account.Account;
import account.AccountManager;

public class TestAccountManager {

	@Test
	public void test() {

		File dir = new File("accounts" + File.separator);
		for (File file : dir.listFiles())
			file.delete();
		AccountManager manager = AccountManager.getInstance();

		assertNotEquals(null, manager);

		assertEquals("admin", manager.getAccounts().get(0).getLogin());

		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("admin", "admin", true));
		accounts.add(new Account("a1", "1", true));
		accounts.add(new Account("a2", "2", true));
		accounts.add(new Account("a3", "3", true));
		accounts.add(new Account("a4", "4", true));
		accounts.add(new Account("a5", "5", true));
		accounts.add(new Account("a6", "6", true));
		accounts.add(new Account("a7", "7", true));

		manager.createAccount("admin", "admin", true);
		manager.createAccount("a1", "1", true);
		manager.createAccount("a2", "2", true);
		manager.createAccount("a3", "3", true);
		manager.createAccount("a4", "4", true);
		manager.createAccount("a5", "5", true);
		manager.createAccount("a6", "6", true);
		manager.createAccount("a7", "7", true);
		// Duplicate
		manager.createAccount("a7", "7", true);
		manager.createAccount("a7", "8", true);

		assertEquals(accounts.size(), manager.getAccounts().size());
		
		for (int i = 0; i < accounts.size(); i++) {
			assertEquals(accounts.get(i).getLogin(),
					manager.getAccounts().get(i).getLogin());
			assertEquals(accounts.get(i).getPassword(), manager.getAccounts()
					.get(i).getPassword());
		}
		char[] cs = { '5' };
		assertEquals(true, manager.isValid("a5", cs));
		assertEquals(false, manager.isValid("a6", cs));

		assertEquals(manager, AccountManager.getInstance());

	}

}
