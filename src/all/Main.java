package all;

import customEnum.Status;
import account.AccountManager;
import claim.Claim;
import claim.ClaimManager;
import claim.processClaim;
import client.ClientManager;
import finance.FinanceManager;
import gui.GUILogin;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ClaimManager cm = ClaimManager.getInstance();
		AccountManager.getInstance();
		ClientManager.getInstance();
		FinanceManager.getInstance();

		new GUILogin();

		while (true) {
			Claim c = cm.takeClaim();
			c.setStatus(Status.PROCESSING);
			new processClaim(c).start();

		}

	}

}
