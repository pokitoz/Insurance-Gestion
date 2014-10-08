package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import account.AccountManager;


public class GUILogin extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField login = null;
	private JPasswordField password = null;
	private JPanel panel = null;

	private JTextPane erreur = null;

	public GUILogin() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JTextPane logintext = new JTextPane();
		logintext.setText("Login");
		logintext.setEditable(false);
		logintext.setFocusable(false);
		logintext.setBackground(getBackground());
		panel.add(logintext);

		login = new JTextField(20);
		
		panel.add(login);

		JTextPane passwordtext = new JTextPane();
		passwordtext.setText("Password");
		passwordtext.setEditable(false);
		passwordtext.setFocusable(false);
		passwordtext.setBackground(getBackground());
		panel.add(passwordtext);

		password = new JPasswordField(20);
		panel.add(password);

		erreur = new JTextPane();
		erreur.setText("");
		erreur.setEditable(false);
		erreur.setBackground(getBackground());
		panel.add(erreur);

		JButton valid = new JButton("Valid");
		valid.addActionListener(this);
		panel.add(valid);

		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);

		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Login");
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		center.x -= this.getBounds().getSize().getWidth()/2;
		center.y -= this.getBounds().getSize().getHeight()/2;
		this.setLocation(center);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Valid")) {
			if (AccountManager.getInstance().isValid(login.getText(),
					password.getPassword())) {
				// TODO
				JFrame frame = new JFrame();
				frame.add(new GUIMenu());
				frame.setVisible(true);
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.pack();
				this.dispose();
				System.out.println("Password accepted");
			} else {
				erreur.setText("Wrong password!");
			}
		}

	}

}
