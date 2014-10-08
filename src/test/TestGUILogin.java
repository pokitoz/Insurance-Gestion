package test;

import gui.GUILogin;

import org.junit.Test;

import account.AccountManager;

import client.ClientManager;


public class TestGUILogin {

	@Test
	public void test() {

		ClientManager clientManager = ClientManager.getInstance();
		clientManager.createClient("Test", "test", " ", 1995, "", "", 5, 2005,
				1005);
		clientManager.createClient("a", "a", " ", 1995, "", "", 5, 2005, 1005);
		clientManager.createClient("b", "a", " ", 1995, "", "", 5, 2005, 1005);
		clientManager.createClient("c", "a", " ", 1995, "", "", 5, 2005, 1005);
		clientManager.createClient("d", "a", " ", 1995, "", "", 5, 2005, 1005);

//		clientManager.getClients();
		AccountManager manager = AccountManager.getInstance();
		manager.createAccount("admin", "admin", true);
		GUILogin test = new GUILogin();
		test.getBackground();
//		while(true){}
	}

}
