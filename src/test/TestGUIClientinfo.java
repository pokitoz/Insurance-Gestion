package test;

import gui.GUIClientDetails;

import org.junit.Test;

import customEnum.ClaimType;
import customEnum.Status;


import claim.Claim;
import client.Client;

public class TestGUIClientinfo {

	@Test
	public void test() {
	
		Client client = new Client("Surname", "FirstName", "test@test.ch",
				1993, "Brand", "Model", 4, 4, 2000);
		
		Claim a = new Claim(0, client, ClaimType.SIMPLE);
		a.setGarageCost(1000);
		a.setStatus(Status.REJECTED);
		
		client.addClaim(a);
		
		Claim b = new Claim(0, client, ClaimType.COMPLEX);
		b.setGarageCost(6000);
		b.setStatus(Status.ACCEPTED);
		
		client.addClaim(b);
		
		Claim c = new Claim(0, client, ClaimType.SIMPLE);
		c.setGarageCost(5000);
		c.setStatus(Status.FAILED);
		
		client.addClaim(c);
		
		new GUIClientDetails(client);
		
//		while(true){
//			
//		}
	}

}
