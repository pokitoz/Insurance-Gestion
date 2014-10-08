package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import client.Client;
import client.ClientManager;

public class GUIClientTable extends JPanel implements Observer, MouseListener {

	private static final long serialVersionUID = 1L;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTable table;
	private CustomTableModel model;

	public CustomTableModel getModel() {
		return model;
	}

	public GUIClientTable() {
		setLayout(new BorderLayout());

		String[] column = { "Surname", "First name", "Birth Date", "Car Brand",
				"Car Model" };
		model = new CustomTableModel();
		model.setColumnIdentifiers(column);
		table = new JTable(model);

		sorter = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sorter);

		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
		table.addMouseListener(this);

		fillTable();
	}

	private void fillTable() {
		for (Client c : ClientManager.getInstance().getClients()) {
			addClient(c);
		}

	}

	public void deleteElements(){
		model.getDataVector().removeAllElements();
		table.getRowSorter().modelStructureChanged();

	}
	
	@Override
	public void update(Observable o, Object arg) {
		model.getDataVector().removeAllElements();
		table.getRowSorter().modelStructureChanged();

		fillTable();
	}

	public void addClient(Client c) {
		Vector<Object> v = new Vector<>();

		v.add(c.getSurname());
		v.add(c.getFirstname());
		v.add(c.getYear());
		v.add(c.getBrand());
		v.add(c.getModel());

		model.addRow(v);
	}

	public void newFilter(String surname) {

		RowFilter<DefaultTableModel, Object> rf = null;

		try {
			rf = RowFilter.regexFilter(surname, 0);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}

		sorter.setRowFilter(rf);
	}

	public void resetFilter() {
		sorter.setRowFilter(null);
	}

	public class CustomTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			ClientManager cm = ClientManager.getInstance();
			int rowClick = table.getSelectedRow();
			if (rowClick != -1) {
				String surname = (String) table.getValueAt(rowClick, 0);
				String firstname = (String) table.getValueAt(rowClick, 1);
				new GUIClientDetails(cm.retriveClient(surname, firstname));
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
