package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import account.Account;

public class TestAccount {

	@Test
	public void test() {
		File dir = new File("accounts" + File.separator);
		for(File file: dir.listFiles()) file.delete();
		
		assertEquals(false, new File("accounts" + File.separator + "Test.account").exists());

		Account acc = new Account("Test", "Password", true);
		
		assertEquals("Test", acc.getLogin());
		assertEquals("Password", acc.getPassword());
		assertEquals(true, 	acc.isAdmin());
		acc.saveAccount();
		assertEquals(true, new File("accounts" + File.separator + acc.getLogin() + ".account").exists());
	
		acc = new Account("Test2", "Password", false);
		assertEquals(false, acc.isAdmin());
		
		
		assertEquals("Test2 Password", acc.toString());
	}

}
