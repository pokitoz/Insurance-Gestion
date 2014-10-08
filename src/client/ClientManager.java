package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class ClientManager extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 455525579968472633L;

	private ArrayList<Client> clients = null;
	private static ClientManager instance;

	private ClientManager() {
		clients = new ArrayList<Client>();
		retrieveClients();
	}

	public static ClientManager getInstance() {
		if (instance == null) {
			instance = new ClientManager();
		}

		return instance;

	}

	public void createClient(String surname, String firstname, String email,
			int year, String brand, String model, int car_seats, int car_year,
			int license_year) {
		File f = new File("clients" + File.separator + surname + firstname
				+ ".client");
		if (f.exists())
			return;

		Client c = new Client(surname, firstname, email, year, brand, model,
				car_seats, car_year, license_year);

		setChanged();
		notifyObservers();
		clients.add(c);

	}

	public Client retriveClient(String surname, String firstname) {
		for (Client client : clients) {
			if (client.getSurname().equals(surname)
					&& client.getFirstname().equals(firstname)) {
				return client;
			}
		}
		return null;
		// File f = new File("clients/" + surname + firstname + ".client");
		// if (!f.exists()) {
		// return null;
		// }
		//
		// ObjectInputStream ois = null;
		// Client c = null;
		// try {
		// ois = new ObjectInputStream(new FileInputStream(f));
		// c = (Client) ois.readObject();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// ois.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		//
		// return c;

	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	private void retrieveClients() {

		File directory = new File("clients" + File.separator);
		File[] clientFiles = directory.listFiles();

		for (File file : clientFiles) {
			createFromFile(file);
		}

		setChanged();
		notifyObservers();
	}

	private void createFromFile(File file) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Client temp = (Client) ois.readObject();
			clients.add(temp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
