package gui;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import client.Client;

public class GUIClientDetails extends JFrame {
	
	private static final long serialVersionUID = 526042566284287589L;
	private JPanel main;
	
	
	public GUIClientDetails(Client c){
		main  = new JPanel();
		
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		JTextPane title = new JTextPane();
		title.setText(c.getSurname()+" "+c.getFirstname()+" Informations");
		title.setEditable(false);
		title.setFocusable(false);
		Font f = new Font(Font.SERIF, Font.BOLD, 20);
		title.setFont(f);
		
		main.add(title);
		
		JTextPane surname = new JTextPane();
		surname.setText("Surname: "+c.getSurname());
		surname.setEditable(false);
		surname.setFocusable(false);
		
		main.add(surname);
		
		JTextPane firstname = new JTextPane();
		firstname.setText("First name: "+c.getFirstname());
		firstname.setEditable(false);
		firstname.setFocusable(false);

		main.add(firstname);
		
		JTextPane date = new JTextPane();
		date.setText("Birth Date: "+c.getYear());
		date.setEditable(false);
		date.setFocusable(false);

		main.add(date);
		
		JTextPane email = new JTextPane();
		email.setText("Email addres: "+c.getEmail());
		email.setEditable(false);
		email.setFocusable(false);

		main.add(email);
		
		JTextPane licenceYear = new JTextPane();
		licenceYear.setText("Licence year: "+c.getLicenseYear());
		licenceYear.setEditable(false);
		licenceYear.setFocusable(false);

		main.add(licenceYear);
		
		JTextPane carTitle = new JTextPane();
		carTitle.setText("Vehicle informations");
		carTitle.setEditable(false);
		carTitle.setFocusable(false);
		carTitle.setFont(f);
		
		main.add(carTitle);
		
		JTextPane brand = new JTextPane();
		brand.setText("Brand: "+c.getBrand());
		brand.setEditable(false);
		brand.setFocusable(false);

		main.add(brand);
		
		JTextPane model = new JTextPane();
		model.setText("Model: "+c.getModel());
		model.setEditable(false);
		model.setFocusable(false);

		main.add(model);
		
		JTextPane carSeats = new JTextPane();
		carSeats.setText("Number of seats: "+c.getCarSeats());
		carSeats.setEditable(false);
		carSeats.setFocusable(false);

		main.add(carSeats);
		
		JTextPane carYear = new JTextPane();
		carYear.setText("First name: "+c.getCarYear());
		carYear.setEditable(false);
		carYear.setFocusable(false);

		main.add(carYear);
		
		JTextPane claimsTitle = new JTextPane();
		claimsTitle.setText("Claims historic");
		claimsTitle.setEditable(false);
		claimsTitle.setFocusable(false);
		claimsTitle.setFont(f);
		
		main.add(claimsTitle);
		
		JTextArea historic = new JTextArea();
		historic.setEditable(false);
		historic.setFocusable(false);
		String s = "";
		for (String string : c.getHistoric()) {
			s += string+ System.getProperty("line.separator");
		}
		historic.setText(s);
		
		main.add(historic);
		
		this.setTitle("Client Informations");
		this.add(main);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		center.x -= this.getBounds().getSize().getWidth()/2;
		center.y -= this.getBounds().getSize().getHeight()/2;
		this.setLocation(center);
		this.setVisible(true);
	}

}
