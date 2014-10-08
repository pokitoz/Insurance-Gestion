package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import finance.FinanceRecord;

public class TestFinanceRecord {

	@Test
	public void test() {

		double claimCost = 50;
		int claimId = 2;
		Date date = new Date();
		FinanceRecord financeRecord = new FinanceRecord("Surname", "FirstName",
				claimId, claimCost, date);

		assertEquals(claimCost, financeRecord.getClaimCost(), 0);
		assertEquals(claimId, financeRecord.getClaimId());
		assertEquals("FirstName", financeRecord.getClientFirstname());
		assertEquals("Surname", financeRecord.getClientSurname());
		assertEquals(date, financeRecord.getDate());

		assertEquals("Surname FirstName " + claimId + " " + claimCost + " "
				+ date.toString(), financeRecord.toString());

	}

}
