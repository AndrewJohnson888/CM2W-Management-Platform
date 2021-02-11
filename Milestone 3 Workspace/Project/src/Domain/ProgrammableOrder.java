package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This class represents a programmable order. It has a ProgrammableCommand which it uses to create a command json string
 * 		which will be sent to a controller. 
 * 		Programmable orders can have condiments, which makes the createCommand function of this class different from the other
 * 		order classes. 
 *
 */
public class ProgrammableOrder extends Order {
	
	public ProgrammableOrder(int orderID, int appID, Coffee coffee, int controllerID, int machineID){
		
		this.behavior = new ProgrammableCommand();
		
		this.orderID = orderID;
		this.appID = appID;
		this.drink = coffee;
		this.controllerID = controllerID;
		this.machineID = machineID;
	}

	@Override
	public String createCommand() {
		
		HashMap<String, Integer> condiments = this.drink.getCondiments();
		ArrayList<String> recipe = this.drink.getRecipe();
		String drinkName = this.drink.getName();
		return this.behavior.createCommand(this.controllerID, this.machineID, this.orderID, drinkName, condiments, recipe);
	}
}
