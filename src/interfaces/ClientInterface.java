package interfaces;

import gui.GUIClientForm;
import claim.Claim;

public class ClientInterface {
	
	
	public static void requestInfo(Claim c){
		//using et form to interact with the client
		new GUIClientForm(c);
	}

	public static void rejectClaim(Claim c) {
		EmailInterface.sendEmail(c.getClient().getEmail(),
				"Your request has been rejected.");
	}

	public static void acceptClaim(Claim c) {
		EmailInterface.sendEmail(c.getClient().getEmail(),
				"Your request has been accepted.");
	}
}
