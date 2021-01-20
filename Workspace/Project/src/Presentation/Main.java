package Presentation;

import Domain.CoffeeProductionSubsystem;

public class Main {

	public static void main(String args[]){
		
		CoffeeProductionSubsystem cps = new CoffeeProductionSubsystem();
		
		for (int i = 0; i < 2; i++) new MobileOrderingApp(cps);
	}
}
