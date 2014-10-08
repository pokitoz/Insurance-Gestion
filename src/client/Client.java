package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import claim.Claim;


public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1801382642923717264L;
	private File file = null;
	private String surname = null;
	private String firstname = null;
	private String email = null;
	private int year;
	private String brand = null;
	private String model = null;
	private int carSeats;
	private int carYear;
	private int licenseYear;
	private ArrayList<String> historic = null;

	public Client(String surname, String firstname, String email, int year,
			String brand, String model, int car_seats, int car_year,
			int license_year) {
		super();
		this.surname = surname;
		this.firstname = firstname;
		this.email = email;
		this.year = year;
		this.brand = brand;
		this.model = model;
		this.carSeats = car_seats;
		this.carYear = car_year;
		this.licenseYear = license_year;

		this.historic = new ArrayList<String>();

		this.file = new File("clients"+File.separator + surname + firstname + ".client");
		saveClient();

	}

	private void saveClient() {

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file, false));
			oos.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addClaim(Claim claim) {
		historic.add("Claim: "+claim.getId()+"	"+claim.getType()+"	Damage cost: "+claim.getGarageCost()+"	"+claim.getDate().toString());
		saveClient();
	}

	public File getFile() {
		return file;
	}

	public String getSurname() {
		return surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public int getYear() {
		return year;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getCarSeats() {
		return carSeats;
	}

	public int getLicenseYear() {
		return licenseYear;
	}

	public ArrayList<String> getHistoric() {
		return historic;
	}

	public int getCarYear() {
		return carYear;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		String clientString = "Surname:\t" + surname + "\n" + "Firstname:\t"
				+ firstname + "\n" + "Email:\t" + email + "\n" + "Year:\t"
				+ year + "\n" + "Brand:\t" + brand + "\n" + "Car seat:\t"
				+ carSeats + "\n" + "Car Year:\t" + carYear + "\n"
				+ "License Year:\t" + licenseYear + "\n" + "Model:\t" + model;
		return clientString;

	}
}
