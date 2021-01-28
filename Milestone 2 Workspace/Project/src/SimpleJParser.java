import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class SimpleJParser extends JParserBehavior {
	
	@Override
	public Order jparse(String jstring) throws ParseException{
		
		JSONObject jobject = (JSONObject) this.parse(jstring);
		JSONObject jorder = this.getObject(jobject, "order");
		JSONObject jaddress = this.getObject(jorder, "address");
		
		int orderID = this.getInteger(jorder, "orderID");
		String street = this.getString(jaddress, "street");
		int zip = this.getInteger(jaddress, "ZIP");
		String zipString = Integer.toString(zip);
		String drink = this.getString(jorder, "drink");

		int controllerID = this.locator.getControllerID(street, zipString, "Simple");
		int coffeeMachineID = this.locator.getCoffeeMachineID(controllerID, "Simple");
		
		Order order = new SimpleOrder(orderID, drink, controllerID, coffeeMachineID);
		
		return order;
	}
}
