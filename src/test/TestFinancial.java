package test;

import org.junit.Test;

import customEnum.ClaimType;
import customEnum.Status;

import claim.Claim;
import client.Client;


import finance.FinanceManager;

public class TestFinancial {

	@Test
	public void test() {
		FinanceManager fm = FinanceManager.getInstance();
		

		Client client = new Client("Surname", "FirstName", "test@test.ch",
				1993, "Brand", "Model", 4, 4, 2000);
		
		Claim a = new Claim(0, client, ClaimType.SIMPLE);
		a.setGarageCost(1000);
		a.setStatus(Status.REJECTED);
		
		fm.addTransaction(a);
		
		Claim b = new Claim(0, client, ClaimType.COMPLEX);
		b.setGarageCost(6000);
		b.setStatus(Status.ACCEPTED);
		
		fm.addTransaction(b);
		
		Claim c = new Claim(0, client, ClaimType.SIMPLE);
		c.setGarageCost(5000);
		c.setStatus(Status.FAILED);
		
		fm.addTransaction(c);
		
		System.out.println(fm.getTransactions());
	}

}
