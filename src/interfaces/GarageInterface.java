package interfaces;

import java.util.Random;

public class GarageInterface {
	
	public static double getCost(String address) throws InterruptedException{
		Thread.sleep(5000);
		
		return new Random().nextDouble()*10000;
		
	}

}
