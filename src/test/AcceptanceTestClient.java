package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.File;

import gui.GUIClientTable;
import gui.GUIClientTable.CustomTableModel;
import gui.GUIMainPanel;

import org.junit.Test;

public class AcceptanceTestClient {

	@Test
	public void test() {

		GUIMainPanel guiMain = new GUIMainPanel();
		GUIClientTable guiClientTable = guiMain.getClientList();
		CustomTableModel model = guiClientTable.getModel();

		guiMain.actionPerformed(new ActionEvent(guiMain.getSearchAll(),
				ActionEvent.ACTION_PERFORMED, "Search All"));

		for (int i = 0; i < model.getRowCount(); i++) {
			String surname = (String) model.getValueAt(i, 0); // Surname
			String firstName = (String) model.getValueAt(i, 1); // Firstname
			assertEquals(true, new File("clients" + File.separator + surname
					+ firstName + ".client").exists());
		}

	}
}
