package gui;

import interfaces.ClientInterface;
import interfaces.EmailInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import customEnum.ClaimType;


import claim.Claim;
import claim.ClaimManager;
import client.Client;
import client.ClientManager;

public class GUICreateClaim extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JTextField surname;
	private JTextField firstName;
	
	private boolean wrongAnswer;
	private JTextPane answer;
	private JTextField email;

	private JComboBox<ClaimType> typeList;
	private JButton send;

	public GUICreateClaim() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.surname = new JTextField();
		this.firstName = new JTextField();
		
		
		this.send = new JButton("Send");

		JTextPane surnameTextPane = new JTextPane();
		JTextPane firstnameTextPane = new JTextPane();

		surnameTextPane.setText("Surname: ");
		surnameTextPane.setFocusable(false);
		surnameTextPane.setEditable(false);
		surnameTextPane.setBackground(getBackground());
		
		this.add(surnameTextPane);
		this.add(surname);

		firstnameTextPane.setText("First name: ");
		firstnameTextPane.setFocusable(false);
		firstnameTextPane.setEditable(false);
		firstnameTextPane.setBackground(getBackground());
		
		this.add(firstnameTextPane);
		this.add(firstName);
		
		 wrongAnswer = false;
		 
		answer = new JTextPane();
		answer.setVisible(false);
		answer.setEditable(false);
		answer.setBackground(getBackground());
		this.add(answer);
		
		email = new JTextField();
		email.setVisible(false);
		this.add(email);
		
		typeList = new JComboBox<ClaimType>();
		typeList.addItem(ClaimType.SIMPLE);
		typeList.addItem(ClaimType.COMPLEX);
		this.add(typeList);
		
		send.addActionListener(this);
		this.add(send);
		
		this.setMaximumSize(new Dimension(5, 5));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!wrongAnswer){
		Client c = ClientManager.getInstance().retriveClient(surname.getText(), firstName.getText());
		
		surname.setEditable(false);
		firstName.setEditable(false);
		
		if(c == null){
			wrongAnswer = true;
			answer.setText("Client not found, enter email address to notify the client");
			answer.setVisible(true);
			email.setVisible(true);
		}else{
			answer.setText("Client found, the claim has been added to the system");
			answer.setVisible(true);
			send.setVisible(false);
			
			//create a new an incompleted claim
			Claim claim = ClaimManager.getInstance().createClaim(c, (ClaimType)typeList.getSelectedItem());
			
			//interface with client to complete the claim
			ClientInterface.requestInfo(claim);
		}
		
		repaint();
		revalidate();		
	}else{
		EmailInterface.sendEmail(email.getText(), "Your claim has been rejected, your not insured in the company");
		JOptionPane.showMessageDialog(this,"An email has been sent too the client");
	}
	}
}
