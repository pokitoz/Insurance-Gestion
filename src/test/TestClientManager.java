package test;

import static org.junit.Assert.*;
import gui.GUIClientTable;

import java.io.File;

import org.junit.Test;

import client.ClientManager;

public class TestClientManager {

	@Test
	public void test() {

		File dir = new File("clients" + File.separator);
		for (File file : dir.listFiles())
			file.delete();

		ClientManager cm = ClientManager.getInstance();
		assertNotEquals(null, cm);

		cm.createClient("Surname", "Firstname", "test@gmail.fr", 1999, "Brand",
				"Model", 4, 2002, 2001);
		assertEquals("Surname", cm.getClients().get(0).getSurname());

		assertEquals("Firstname", cm.retriveClient("Surname", "Firstname")
				.getFirstname());
		assertEquals("Surname", cm.retriveClient("Surname", "Firstname")
				.getSurname());

		assertEquals(0, cm.countObservers());
		cm.addObserver(new GUIClientTable());
		assertEquals(1, cm.countObservers());


		assertEquals(null, cm.retriveClient("Test", "Test"));
	}

}
