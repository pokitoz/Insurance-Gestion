package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import client.ClientManager;


public class GUIMainPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8700745124463066190L;

	private JPanel searchPanel = null;

	private GUIClientTable clientList = null;
	
	public GUIClientTable getClientList() {
		return clientList;
	}

	private JPanel resultPanel = null;

	private JTextField clientSurname = null;

	private JButton search = null;
	private JButton searchAll = null;

	private JTextArea searchResult = null;

	public GUIMainPanel() {
		setLayout(new BorderLayout());

		resultPanel = new JPanel();
		searchPanel = new JPanel();
		clientList = new GUIClientTable();
		ClientManager.getInstance().addObserver(clientList);
	
		JTextPane searchClientSurname = new JTextPane();
		searchClientSurname.setFocusable(false);
		searchClientSurname.setEditable(false);
		searchClientSurname.setText("Surname : ");
		searchClientSurname.setBackground(getBackground());
		searchPanel.add(searchClientSurname);

		clientSurname = new JTextField(10);
		searchPanel.add(clientSurname);

		search = new JButton("Search");
		search.addActionListener(this);
		searchPanel.add(search);

		searchAll = new JButton("See All");
		searchAll.addActionListener(this);
		searchPanel.add(searchAll);

		add(searchPanel, BorderLayout.NORTH);

		searchResult = new JTextArea();
		searchResult.setEditable(false);
		searchResult.setFocusable(false);

		
		resultPanel.add(searchResult);

		add(clientList,BorderLayout.CENTER);
		
		JTextPane openInfo = new JTextPane();
		openInfo.setText("Double click on a client ti display his informations");
		openInfo.setFocusable(false);
		openInfo.setEditable(false);
		
		add(openInfo,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Search")) {
			clientList.newFilter(clientSurname.getText());
		}

		else if (e.getActionCommand().equals("See All")) {
			clientList.resetFilter();
		}

	}

	public Object getSearchAll() {
		// TODO Auto-generated method stub
		return searchAll;
	}
}
