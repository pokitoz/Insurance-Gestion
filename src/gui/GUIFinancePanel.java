package gui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import finance.FinanceManager;
import finance.FinanceRecord;

public class GUIFinancePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 6293627193787319470L;
	private JTextPane total = null;
	
	private TableRowSorter<DefaultTableModel> sorter;
	private JTable table;
	private final String[] column = { "Cl. Surname", "Cl. Firstname", "Claim ID", "Amount",
				"Date" };
	private CustomTableModel model;
	
	
	public GUIFinancePanel(){
		this.setLayout(new BorderLayout());

		
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

		total = new JTextPane();
		total.setFocusable(false);
		total.setEditable(false);
		total.setBackground(getBackground());
		total.setText("Total: "+FinanceManager.getInstance().getTotal());
		this.add(total,BorderLayout.SOUTH);

		FinanceManager.getInstance().addObserver(this);
		fillTable();
	}

	public void fillTable() {
		ArrayList<FinanceRecord> list = FinanceManager.getInstance().getTransactions();
		for (FinanceRecord r : list) {
			addRecord(r);
		}

	}

	public void addRecord(FinanceRecord r) {
		Vector<Object> v = new Vector<>();

		v.add(r.getClientSurname());
		v.add(r.getClientFirstname());
		v.add(r.getClaimId());
		v.add(r.getClaimCost());
		v.add(new SimpleDateFormat("MM-dd-yyyy").format(r.getDate()));

		model.addRow(v);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		model.getDataVector().removeAllElements();
		table.getRowSorter().modelStructureChanged();
		
		fillTable();
		total.setText("Total: "+FinanceManager.getInstance().getTotal());
		repaint();
		revalidate();
	}
	
	private class CustomTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}
}
