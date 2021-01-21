package Domain;

import DataSource.Order;

public class ProduceCoffeeAndCondimentsBehavior implements MachineCapabilityBehavior{

	private boolean isResponding; 
	
	public ProduceCoffeeAndCondimentsBehavior(boolean isResponding){
		
		this.isResponding = isResponding;
	}
	
	@Override
	public boolean makeCoffee(Order o) {
		
		if (! this.isResponding) return false;
		
		return true;
	}

}
