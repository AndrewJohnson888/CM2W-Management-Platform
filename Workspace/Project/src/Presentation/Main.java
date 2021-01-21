package Presentation;

import java.util.concurrent.TimeUnit;

import Domain.CoffeeProductionSubsystem;

/*
 * TODO
 *  
 * SEQUENCE DIAGRAM
 * update with names of classes and methods
 * rewrite with actual process flow
 */

public class Main {
	
	private static final int NUMBER_OF_APPS = 2;
	private static final int DELAY_TIME = 1;

	public static void main(String args[]){
		
		CoffeeProductionSubsystem cps = new CoffeeProductionSubsystem();
		
		for (int i = 0; i < NUMBER_OF_APPS; i++) new MobileOrderingApp(cps);
	}
	
	
	// simulates a small delay as if coffee order was actually being processed and made
	public static void simulateDelay(){
		
		try {
			
			TimeUnit.SECONDS.sleep(DELAY_TIME);
		} 
		
		catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
