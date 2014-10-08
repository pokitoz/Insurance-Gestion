package claim;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

import customEnum.ClaimType;
import customEnum.Status;

import client.Client;


public class ClaimManager extends Observable{

	private LinkedBlockingQueue<Claim> newClaims;
	private LinkedBlockingQueue<Claim> readyToProcessClaims;
	private LinkedBlockingQueue<Claim> processingClaims;
	private LinkedBlockingQueue<Claim> failedClaims;
	private LinkedBlockingQueue<Claim> finishedClaims;

	private static ClaimManager instance = null;
	private int nbId;

	private ClaimManager() {

		newClaims = new LinkedBlockingQueue<Claim>();
		readyToProcessClaims = new LinkedBlockingQueue<Claim>();
		processingClaims = new LinkedBlockingQueue<Claim>();
		failedClaims = new LinkedBlockingQueue<Claim>();
		finishedClaims = new LinkedBlockingQueue<Claim>();
		
		nbId = 0;
	}
	
	public Claim createClaim(Client c, ClaimType t){
		Claim claim = new Claim(nbId, c, t);
		nbId++;
		addClaim(claim, newClaims);
		return claim;
	}

	public static ClaimManager getInstance() {
		if (instance == null) {
			instance = new ClaimManager();
		}
		return instance;
	}
	
	public void updateClaim(Claim c, String contactGarage, String context){
		c.setContext(context);
		c.setGarageContact(contactGarage);
		
		newClaims.remove(c);
		readyToProcessClaims.add(c);
	}
	
	public void finishedClaim(Claim  c){
		processingClaims.remove(c);
		addClaim(c, finishedClaims);
	}
	
	public void failedClaim(Claim c){
		processingClaims.remove(c);
		addClaim(c, failedClaims);
	}
	
	public Claim takeClaim() throws InterruptedException{
		Claim c = readyToProcessClaims.take();
		c.setStatus(Status.PROCESSING);
		addClaim(c, processingClaims);
		
		return c;
	}

	public LinkedBlockingQueue<Claim> getCurrentClaims() {
		return readyToProcessClaims;
	}

	public LinkedBlockingQueue<Claim> getFailedClaims() {
		return failedClaims;
	}

	public LinkedBlockingQueue<Claim> getFinishedClaims() {
		return finishedClaims;
	}

	public void addClaim(Claim claim, LinkedBlockingQueue<Claim> queue) {
		queue.add(claim);
		
		setChanged();
		notifyObservers();
	}
	
	public Claim getClaim(int id){
		for (Claim claim : getClaims()) {
			if(claim.getId() == id){
				return claim;
			}
		}
		
		return null;
	}
	
	//public void updateFinishedClaim
	
	public ArrayList<Claim> getClaims(){
		ArrayList<Claim> claims = new ArrayList<>();
		claims.addAll(newClaims);
		claims.addAll(readyToProcessClaims);
		claims.addAll(processingClaims);
		claims.addAll(failedClaims);
		claims.addAll(finishedClaims);
		
		return claims;
	}
	

}
