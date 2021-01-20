package Domain;

import DataSource.Instruction;

public class ProduceCoffeeOnlyBehavior implements MachineCapabilityBehavior{
	
	private boolean isResponding;
	
	public ProduceCoffeeOnlyBehavior(boolean isResponding){
		
		this.isResponding = isResponding;
	}

	@Override
	public boolean makeCoffee(Instruction i) {
		
		if (! this.isResponding) return false;
		
		return true;
	}
}