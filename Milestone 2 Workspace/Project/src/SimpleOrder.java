import org.json.simple.JSONObject;

public class SimpleOrder extends Order{

	public SimpleOrder(int orderID, String drink, int controllerID, int coffeeMachineID){
		
		this.orderID = orderID;
		this.drink = drink;
		this.controllerID = controllerID;
		this.coffeeMachineID = coffeeMachineID;
		this.orderType= "Simple";
	}

	@Override
	public String constructCommand() {
		
		JSONObject jcommand = new JSONObject();
		jcommand.put("controller_id", this.controllerID);
		jcommand.put("coffee_machine_id", this.coffeeMachineID);
		jcommand.put("orderID", this.orderID);
		jcommand.put("DrinkName", this.drink);
		jcommand.put("Requesttype", this.orderType);
		
		JSONObject jobject = new JSONObject();
		jobject.put("command", jcommand);
		
		return jobject.toJSONString();
	}
}
