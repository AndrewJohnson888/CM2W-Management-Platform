package Domain;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AutomatedOrder extends Order{

	private HashMap<String, Integer> condiments;

	public AutomatedOrder(int orderID, String drink, int controllerID, int coffeeMachineID, HashMap<String, Integer> condiments){
		
		this.orderID = orderID;
		this.drink = drink;
		this.controllerID = controllerID;
		this.coffeeMachineID = coffeeMachineID;
		this.condiments = condiments;
		this.orderType = "Automated";
	}

	@Override
	public String constructCommand() {
		
		JSONArray joptions = new JSONArray();
		for (String key : this.condiments.keySet()){
			
			JSONObject jitem = new JSONObject();
			jitem.put("Name", key);
			jitem.put("qty", this.condiments.get(key));
			
			joptions.add(jitem);
		}
		
		JSONObject jcommand = new JSONObject();
		jcommand.put("controller_id", this.controllerID);
		jcommand.put("coffee_machine_id", this.coffeeMachineID);
		jcommand.put("orderID", this.orderID);
		jcommand.put("DrinkName", this.drink);
		jcommand.put("Requesttype", this.orderType);
		jcommand.put("Options:", joptions);
		
		JSONObject jobject = new JSONObject();
		jobject.put("command", jcommand);
		
		return jobject.toJSONString();
	}
}
