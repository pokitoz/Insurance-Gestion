package finance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;

import claim.Claim;


public class FinanceManager extends Observable{

	private ArrayList<FinanceRecord> transactions = null;
	private static FinanceManager instance = null;

	private FinanceManager() {
		transactions = new ArrayList<FinanceRecord>();
		retrieveRecords();
	}
	
	private void retrieveRecords(){
		File directory = new File("financial"+File.separator);
		File[] recordFiles = directory.listFiles();

		for (File file : recordFiles) {
			createFromFile(file);
		}
		
		setChanged();
		notifyObservers();
	}
	
	private void saveRecord(FinanceRecord finRec,File file) {

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file, false));
			oos.writeObject(finRec);
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
	
	private void createFromFile(File file){
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			FinanceRecord temp = (FinanceRecord) ois.readObject();
			transactions.add(temp);
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

	public ArrayList<FinanceRecord> getTransactions() {
		return transactions;
	}

	public void addTransaction(Claim c) {
		FinanceRecord finRec = new FinanceRecord(c.getClient().getSurname(), c
				.getClient().getFirstname(), c.getId(), c.getGarageCost(), c
				.getDate());
		
		transactions.add(finRec);
		
		saveRecord(finRec, new File("financial"+File.separator +finRec.hashCode() + ".freco"));
		transactions.add(finRec);
		
		setChanged();
		notifyObservers();
	}
	
	public double getTotal(){
		double total = 0;
		for (FinanceRecord fr : transactions) {
			total += fr.getClaimCost();
		}
		return total;
	}

	public static FinanceManager getInstance() {
		if (instance == null) {
			instance = new FinanceManager();
		}

		return instance;
	}
}
