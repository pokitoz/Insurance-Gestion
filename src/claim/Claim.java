package claim;

import java.io.Serializable;
import java.util.Date;

import customEnum.ClaimType;
import customEnum.Status;

import client.Client;

public class Claim implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -543215006369830254L;
	private int id;
	private Client client = null;

	private String context = null;

	private String garageContact = null;
	private double garageCost;

	private ClaimType type = null;
	private Status status = null;
	
	private Date date = null;

	public Claim(int id, Client client,ClaimType type) {
		super();
		this.id = id;
		this.client = client;
		this.status = Status.NEW;
		this.type = type;
		this.date = new Date();
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setGarageContact(String garageContact) {
		this.garageContact = garageContact;
	}

	public void setGarageCost(double garageCost) {
		this.garageCost = garageCost;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setType(ClaimType t){
		type = t;
	}

	public int getId() {
		return id;
	}

	public Client getClient() {
		return client;
	}

	public String getContext() {
		return context;
	}

	public String getGarageContact() {
		return garageContact;
	}

	public double getGarageCost() {
		return garageCost;
	}

	public ClaimType getType() {
		return type;
	}
	
	public Date getDate(){
		return date;
	}

	public Status getStatus() {
		return status;
	}

}
