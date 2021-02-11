package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This is the factory which creates an order and a coffee and associates the two.
 * 		It uses a locator class to help find which machine should create the coffee drink.
 *
 */
public class ConcreteOrderFactory extends OrderFactory{
	
	private ControllerLocator locator;
	
	public ConcreteOrderFactory(){ this.locator = new ControllerLocator(); }

	public Order createOrder(String order, int appID) {
		
		JSONModule jmodule = new JSONModule();
		jmodule.parseOrder(order);
		
		this.locator.locateMachine(jmodule.getCondiments().size() != 0, 
								   jmodule.getDrink(), 
								   jmodule.getStreet(), 
								   jmodule.getZip());
		
		ArrayList<String> recipe = this.locator.getRecipe();
		int machineID            = this.locator.getMachine();
		int controllerID         = this.locator.getController();
		MachineType type         = this.locator.getType();
		
		Coffee coffee = createCoffee(jmodule.getDrink(), 
							         jmodule.getCondiments(), 
							         recipe);
		
		switch(type){
		
			case SIMPLE:
				return new SimpleOrder(jmodule.getOrderID(), appID, coffee, controllerID, machineID);
			case AUTOMATED: 
				return new AutomatedOrder(jmodule.getOrderID(), appID, coffee, controllerID, machineID);
			case PROGRAMMABLE: 
				return new ProgrammableOrder(jmodule.getOrderID(), appID, coffee, controllerID, machineID);
		}
		
		return null;
	}
	
	private Coffee createCoffee(String drink, HashMap<String, Integer> condiments, ArrayList<String> recipe){
		
		Coffee coffee = new BaseCoffee(drink, recipe);
		
		for (String condiment : condiments.keySet()){
			
			for (int i = 0; i < condiments.get(condiment); i++){
			
				coffee = new CondimentDecorator(condiment, coffee);
			}
		}
		
		return coffee;
	}
}