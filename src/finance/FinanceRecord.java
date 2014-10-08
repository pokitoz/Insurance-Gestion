package finance;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class FinanceRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9182844401414398426L;
	private int id;
	private String clientSurname = null;
	private String clientFirstname = null;
	private int claimId;
	private double claimCost;
	private Date date = null;

	public FinanceRecord(String clientSurname, String clientFirstname,
			int claimId, double claimCost, Date date) {
		this.id = new Random().nextInt();
		this.clientSurname = clientSurname;
		this.clientFirstname = clientFirstname;
		this.claimId = claimId;
		this.claimCost = claimCost;
		this.date = date;
	}

	public String getClientSurname() {
		return clientSurname;
	}

	public String getClientFirstname() {
		return clientFirstname;
	}

	public int getClaimId() {
		return claimId;
	}

	public double getClaimCost() {
		return claimCost;
	}

	public Date getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		String tmp = clientSurname+" "+clientFirstname+" "+claimId+" "+claimCost+" "+date.toString();
		return tmp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(claimCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + claimId;
		result = prime * result
				+ ((clientFirstname == null) ? 0 : clientFirstname.hashCode());
		result = prime * result
				+ ((clientSurname == null) ? 0 : clientSurname.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		return result;
	}

	

}
