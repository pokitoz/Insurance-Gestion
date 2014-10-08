package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GUIMenu extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2240535671153800340L;

	JPanel menu = null;
	JPanel mainPanel = null;
 
	GUIMainPanel guiMainPanel = null;
	GUIClaimTable guiClaimList = null;
	GUIFinancePanel guiFinancePanel = null;
	
	
	
	JButton b1 = null;
	JButton b2 = null;
	JButton b3 = null;
	JButton b4 = null;

	public GUIMenu() {
		menu = new JPanel();
		menu.setLayout(new GridLayout(4, 1));

		this.setLayout(new BorderLayout());

		b1 = new JButton("Clients");
		b1.addActionListener(this);
		menu.add(b1);

		b2 = new JButton("Claims");
		b2.addActionListener(this);
		menu.add(b2);

		b3 = new JButton("Add claim");
		b3.addActionListener(this);
		menu.add(b3);

		b4 = new JButton("Finance Manager");
		b4.addActionListener(this);
		menu.add(b4);

		this.add(menu, BorderLayout.WEST);

		mainPanel = new JPanel();

		guiMainPanel = new GUIMainPanel();
		mainPanel = guiMainPanel;
		
		guiClaimList = new GUIClaimTable();
		guiFinancePanel = new GUIFinancePanel();

		this.add(mainPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.remove(mainPanel);
		if (e.getActionCommand().equals("Clients")) {
			mainPanel = guiMainPanel;
			this.add(mainPanel, BorderLayout.CENTER);

		} else if (e.getActionCommand().equals("Claims")) {
			mainPanel = guiClaimList;
			this.add(mainPanel, BorderLayout.CENTER);

		} else if (e.getActionCommand().equals("Add claim")) {
			mainPanel = new GUICreateClaim();
			this.add(mainPanel, BorderLayout.CENTER);

		} else if (e.getActionCommand().equals("Finance Manager")) {
			mainPanel = guiFinancePanel;
			this.add(mainPanel, BorderLayout.CENTER);

		}
		repaint();
		revalidate();
	}
}
