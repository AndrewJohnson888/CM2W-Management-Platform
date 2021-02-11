package Domain;

/**
 * 
 * @author johnsoa8
 * Description: This class represents a simple order. It has an SimpleCommand which it uses to create a command json string
 * 		which will be sent to a controller. 
 * 		Simple orders can have condiments, which makes the createCommand function of this class different from the other
 * 		order classes. 
 *
 */
public class SimpleOrder extends Order {

	public SimpleOrder(int orderID, int appID, Coffee coffee, int controllerID, int machineID){
		
		this.behavior = new SimpleCommand();
		
		this.orderID = orderID;
		this.appID = appID;
		this.drink = coffee;
		this.controllerID = controllerID;
		this.machineID = machineID;
	}

	@Override
	public String createCommand(){
		
		String drinkName = this.drink.getName();
		return this.behavior.createCommand(this.controllerID, this.machineID, this.orderID, drinkName, null, null);
	}
}
