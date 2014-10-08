package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import claim.Claim;
import claim.ClaimManager;



public class GUIClaimTable extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTable table;
	private final String[] column = { "Id Num", "Client", "Type", "Status",
			"Cost" };
	private CustomTableModel model;

	public GUIClaimTable() {
		setLayout(new BorderLayout());

		model = new CustomTableModel();
		model.setColumnIdentifiers(column);
		table = new JTable(model);

		sorter = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sorter);
		

		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for (Enumeration<TableColumn> e = table.getColumnModel().getColumns(); e
				.hasMoreElements();) {
			e.nextElement().setCellRenderer(new CustomCellRenderer());
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);

		ClaimManager.getInstance().addObserver(this);
	}

	public void fillTable() {
		ArrayList<Claim> list = ClaimManager.getInstance().getClaims();
		for (Claim c : list) {
			addClaim(c);
		}

	}

	public void addClaim(Claim c) {
		Vector<Object> v = new Vector<>();

		v.add(c.getId());
		v.add(c.getClient().getSurname());
		v.add(c.getType());
		v.add(c.getStatus());
		v.add(c.getGarageCost());

		model.addRow(v);
	}

	@Override
	public void update(Observable o, Object arg) {
		model.getDataVector().removeAllElements();
		table.getRowSorter().modelStructureChanged();

		fillTable();
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

	private class CustomCellRenderer extends DefaultTableCellRenderer {

	
		private static final long serialVersionUID = 6737000812830474503L;

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component cell = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			
			Claim c = ClaimManager.getInstance().getClaim((int)table.getValueAt(row, 0));
			
			//System.err.println(c.getStatus().getColor());
			cell.setBackground(c.getStatus().getColor());
			
			return cell;
		}
	}

}
