package Domain;

import DataSource.Instruction;

public class ProduceCoffeeAndCondimentsBehavior implements MachineCapabilityBehavior{

	private boolean isResponding;
	
	public ProduceCoffeeAndCondimentsBehavior(boolean isResponding){
		
		this.isResponding = isResponding;
	}
	
	@Override
	public boolean makeCoffee(Instruction i) {
		
		if (! this.isResponding) return false;
		
		return true;
	}

}
