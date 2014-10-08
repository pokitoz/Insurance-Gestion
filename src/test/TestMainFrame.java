package test;

import gui.GUIMenu;

import javax.swing.JFrame;

import org.junit.Test;

import customEnum.ClaimType;

import claim.ClaimManager;
import client.Client;


public class TestMainFrame {

	@Test
	public void test() throws InterruptedException {
		JFrame frame = new JFrame();
		frame.add(new GUIMenu());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		ClaimManager cm = ClaimManager.getInstance();
		
		Client c1 = new Client("Engilberge", "Martin", null, 1993, "Ford", "Test", 4, 2004, 2011);
		Client c2 = new Client("Depraz", "Florian", null, 1992, "Renault", "AAAA", 4, 2004, 2011);
		
		cm.createClaim(c1, ClaimType.SIMPLE);
		cm.createClaim(c2, ClaimType.COMPLEX);
		
		
	//	while(true){
			
	//	}
	}

}
