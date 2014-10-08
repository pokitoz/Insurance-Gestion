package claim;

import customEnum.ClaimType;
import customEnum.Status;
import finance.FinanceManager;
import interfaces.ClientInterface;
import interfaces.GarageInterface;

public class processClaim extends Thread {
	Claim c = null;
	ClaimManager cm = null;
	
	public processClaim(Claim c){
		this.c = c;
		cm = ClaimManager.getInstance();
	}
	
	public void run() {
		
		c.setStatus(Status.PROCESSING);
		
		//First step in processing a claim
		Status tmp = checkInsurance(c);

		//Second step in processing a claim only if the claim is complex
		if (c.getType() == ClaimType.COMPLEX && tmp == Status.ACCEPTED) {
			tmp = checkDamageHistory(c);
		}

		//Last step in processing a claim
		if (tmp == Status.ACCEPTED) {
			tmp = contactGarage(c);
		}

		
		c.setStatus(tmp);

		// Add the claim to the client history
		c.getClient().addClaim(c);
		switch (tmp) {
		case ACCEPTED:
			FinanceManager.getInstance().addTransaction(c);
			ClientInterface.acceptClaim(c);
			cm.finishedClaim(c);
			break;
		case REJECTED:
			ClientInterface.rejectClaim(c);
			cm.finishedClaim(c);
			break;
		case FAILED:
			cm.failedClaim(c);
			break;

		default:
			break;
		}		
	}
	
	public Status checkInsurance(Claim claim){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Status.ACCEPTED;
	}

	public Status checkDamageHistory(Claim claim) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Status.ACCEPTED;
	}

	public Status contactGarage(Claim claim) {
		try {
			claim.setGarageCost(GarageInterface.getCost(claim.getGarageContact()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Status.ACCEPTED;
	}
}
