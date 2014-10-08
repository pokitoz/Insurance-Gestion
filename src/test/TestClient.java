package test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import claim.Claim;
import client.Client;


public class TestClient {

	@Test
	public void test() {
		int year = 1993;
		int car_year = 2008;
		int car_seats = 4;
		int license_year = 2009;
		Client c = new Client("Surname", "FirstName", "email@client.com", year, "Brand", "Model",
				car_seats, car_year, license_year);

		assertEquals("Surname", c.getSurname());
		assertEquals("FirstName", c.getFirstname());

		assertEquals(1993, c.getYear());

		assertEquals("Brand", c.getBrand());
		assertEquals("Model", c.getModel());

		assertEquals(4, c.getCarSeats());
		assertEquals(license_year, c.getLicenseYear());

		assertEquals(new ArrayList<Claim>(), c.getHistoric());

		assertEquals("clients" + File.separator+ "SurnameFirstName" + ".client", c
				.getFile().getPath());
		
		

	}

}
