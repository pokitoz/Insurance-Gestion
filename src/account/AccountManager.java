package account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

//TODO SINGLETON MA?AGER
public class AccountManager implements Serializable {

	private static AccountManager instance = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5986242393964140132L;
	private ArrayList<Account> accounts = null;

	private AccountManager() {
		accounts = new ArrayList<Account>();
		File directory = new File("accounts"+File.separator);
		File[] filesacc = directory.listFiles();
		
		createAccount("admin", "admin", true);
		
		for (File file : filesacc) {
			createFromFile(file);
		}

	}

	public static AccountManager getInstance() {
		if (instance == null) {
			instance = new AccountManager();
		}
		return instance;
	}

	private void createFromFile(File file) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));

			accounts.add((Account) ois.readObject());

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

	public void createAccount(String log, String password, boolean admin) {

		File f = new File("accounts"+File.separator + log + ".account");
		if (f.exists())
			return;

		Account acc = new Account(log, password, admin);
		acc.saveAccount();
		accounts.add(acc);
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public boolean isValid(String login, char[] cs) {
		for (Account acc : accounts) {
			String password = acc.getPassword();
			if (acc.getLogin().equals(login) && cs.length == password.length()) {
				for (int i = 0; i < password.length(); i++) {
					if (password.charAt(i) != cs[i]) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}
