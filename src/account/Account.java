package account;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2153988183172745832L;
	private String login = null;
	private String password = null;
	private boolean admin;

	public Account(String login, String password, boolean admin) {
		this.login = login;
		this.password = password;
		this.admin = admin;

	}

	public void saveAccount() {

		File file = new File("accounts"+File.separator + login + ".account");
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

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return admin;
	}

	@Override
	public String toString() {
		return login + " " + password;
	}
}
