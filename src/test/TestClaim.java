package test;

import static org.junit.Assert.*;

import org.junit.Test;

import customEnum.ClaimType;
import customEnum.Status;
import claim.Claim;
import claim.ClaimManager;
import client.Client;


public class TestClaim {

	@Test
	public void test() {

		
		int id = 0;
		Client client = new Client("Surname", "FirstName", "test@test.ch",
				1993, "Brand", "Model", 4, 4, 2000);
		Claim c = ClaimManager.getInstance().createClaim(client, ClaimType.SIMPLE);

		assertEquals(id, c.getId());
		assertEquals(client, c.getClient());

		assertEquals(ClaimType.SIMPLE, c.getType());
		assertEquals(Status.NEW, c.getStatus());

		c.setContext("Context");
		assertEquals("Context", c.getContext());

		c.setGarageContact("test@test.com");
		assertEquals("test@test.com", c.getGarageContact());

		c.setStatus(Status.ACCEPTED);
		assertEquals(Status.ACCEPTED, c.getStatus());

		c.setGarageCost(50);
		assertEquals(50, c.getGarageCost(),0);

	}

}
