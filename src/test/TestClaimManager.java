package test;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

import customEnum.ClaimType;

import claim.Claim;
import claim.ClaimManager;
import client.Client;


public class TestClaimManager {

	@Test
	public void test() {

		ClaimManager cm = ClaimManager.getInstance();
	
		int year = 1993;
		int car_year = 2008;
		int car_seats = 4;
		int license_year = 2009;
		Client c = new Client("Surname", "FirstName", "email@client.com", year,
				"Brand", "Model", car_seats, car_year, license_year);

		Claim claim = cm.createClaim(c, ClaimType.SIMPLE);
		LinkedBlockingQueue<Claim> claims = new LinkedBlockingQueue<Claim>();
		claims.add(claim);
		//assertEquals(claims, cm.getCurrentClaims());
	}

}
