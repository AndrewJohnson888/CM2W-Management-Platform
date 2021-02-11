package Domain;

import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This class represents an automated order. It has an AutomatedCommand which it uses to create a command json string
 * 		which will be sent to a controller. 
 * 		Automated orders can have condiments, which makes the createCommand function of this class different from the other
 * 		order classes. 
 *
 */
public class AutomatedOrder extends Order {

	public AutomatedOrder(int orderID, int appID, Coffee coffee, int controllerID, int machineID){
		
		this.behavior = new AutomatedCommand();
		
		this.orderID = orderID;
		this.appID = appID;
		this.drink = coffee;
		this.controllerID = controllerID;
		this.machineID = machineID;
	}

	@Override
	public String createCommand() {
		
		HashMap<String, Integer> condiments = this.drink.getCondiments();
		String drinkName = this.drink.getName();
		return this.behavior.createCommand(this.controllerID, this.machineID, this.orderID, drinkName, condiments, null);
	}
}
