package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import claim.Claim;
import claim.ClaimManager;


public class GUIClientForm  extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;
	private JPanel main = null;
	private Claim claim = null;
	
	private JTextPane warning = null;
	private JTextField contactGarage = null;
	private JTextArea context = null;
	private JButton send = null;
	
	
	public GUIClientForm(Claim c){
		main = new JPanel();
		this.add(main);
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		this.claim = c;
		JTextPane title = new JTextPane();
		title.setText("Claim Form number: "+claim.getId());
		title.setEditable(false);
		title.setFocusable(false);
		Font f = new Font(Font.SERIF, Font.BOLD, 20);
		title.setFont(f);
		
		main.add(title);
		
		warning = new JTextPane();
		warning.setText("Please, complete all the fields of the form");
		warning.setEditable(false);
		warning.setFocusable(false);
		warning.setVisible(false);
		
		main.add(warning);
		
		JTextPane garageJTextPane = new JTextPane();
		garageJTextPane.setText("Garage email: ");
		garageJTextPane.setFocusable(false);
		garageJTextPane.setEditable(false);
		main.add(garageJTextPane);
		
		contactGarage = new JTextField();
		main.add(contactGarage);
		
		JTextPane contextJPane = new JTextPane();
		contextJPane.setText("Context of the accident: ");
		contextJPane.setEditable(false);
		contextJPane.setFocusable(false);
		main.add(contextJPane);
		
		context = new JTextArea(20,30);
		context.setLineWrap(true);
		
		main.add(new JScrollPane(context));
		
		send = new JButton("Send");
		send.addActionListener(this);
		main.add(send);
		
		this.setTitle("Form " + claim.getClient().getSurname()+" "+claim.getClient().getFirstname());
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(contactGarage.getText().equals("") || context.getText().equals("")){
			warning.setVisible(true);
		}
		else{
			ClaimManager.getInstance().updateClaim(claim, contactGarage.getText(), context.getText());
			JOptionPane.showMessageDialog(this,"Claim completed, thank you");
			this.dispose();
		}
		
		
	}
	
}
