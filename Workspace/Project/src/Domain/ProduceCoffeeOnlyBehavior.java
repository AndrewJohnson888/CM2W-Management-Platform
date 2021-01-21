package Domain;

import DataSource.Order;
import Presentation.Main;

public class ProduceCoffeeOnlyBehavior implements MachineCapabilityBehavior{
	
	private boolean isResponding; // simulates if the controller is responding to CPS commands
	
	public ProduceCoffeeOnlyBehavior(boolean isResponding){
		
		this.isResponding = isResponding;
	}

	@Override
	public boolean makeCoffee(Order o) {
		
		if (! this.isResponding) return false;
		
		System.out.println();
		System.out.println("Beginning to make order " + o.getOrderIDInteger());
		
		Main.simulateDelay();
		
		for (String r : o.getRecipe()){
			
			System.out.println("\t Adding " + r);
			
			Main.simulateDelay();
		}
		
		o.setStatusMessage("complete");
		System.out.println("coffee order has been made");
		
		return true;
	}
}
