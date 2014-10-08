package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import customEnum.ClaimType;
import claim.Claim;
import client.Client;
import finance.FinanceManager;

public class TestFinangeManager {

	@Test
	public void test() {

		File dir = new File("financial" + File.separator);
		for (File file : dir.listFiles())
			file.delete();

		Client c = new Client("Surname", "FirstName", "email@client.com", 1993,
				"Brand", "Model", 4, 2002, 2000);

		Client c2 = new Client("Surname2", "FirstName2", "email@client.com",
				1993, "Brand", "Model", 4, 2002, 2000);

		FinanceManager manager = FinanceManager.getInstance();

		assertEquals(0, manager.countObservers());
		// manager.addObserver(null);

		double cost1 = 50.25;
		double cost2 = 20.75;
		double cost3 = 20.20;
		double cost4 = 50.20;
		assertEquals(0, manager.getTotal(), 0);
		Claim claim1 = new Claim(0, c, ClaimType.SIMPLE);
		claim1.setGarageCost(cost1);
		Claim claim2 = new Claim(1, c, ClaimType.COMPLEX);
		claim2.setGarageCost(cost2);
		Claim claim3 = new Claim(2, c2, ClaimType.SIMPLE);
		claim3.setGarageCost(cost3);
		Claim claim4 = new Claim(3, c2, ClaimType.COMPLEX);
		claim4.setGarageCost(cost4);

		manager.addTransaction(claim1);
		manager.addTransaction(claim2);
		manager.addTransaction(claim3);
		manager.addTransaction(claim4);

		assertNotEquals(0, manager.getTotal(), 0);

		ArrayList<Claim> claims = new ArrayList<Claim>();
		claims.add(claim1);
		claims.add(claim2);
		claims.add(claim3);
		claims.add(claim4);

		assertEquals(4, manager.getTransactions().size());
		for (int i = 0; i < 4; i++) {
			assertEquals(claims.get(i).getId(), manager.getTransactions()
					.get(i).getClaimId());
			assertEquals(claims.get(i).getClient().getFirstname(), manager
					.getTransactions().get(i).getClientFirstname());
		}

	}

}
