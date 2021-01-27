import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class SimpleJParser extends JParserBehavior {

	public SimpleJParser(){
		
	}
	
	@Override
	public Order jparse(String jstring) throws ParseException{
		
		JSONObject jobject = (JSONObject) this.parse(jstring);
		JSONObject jorder = this.getObject(jobject, "order");
		JSONObject jaddress = this.getObject(jobject, "address");
		
		int orderID = this.getInteger(jorder, "orderID");
		String street = this.getString(jaddress, "street");
		String zip = this.getString(jaddress, "ZIP");
		String drink = this.getString(jorder, "drink");
		
		Order order = new SimpleOrder(orderID, street, zip, drink);
		
		return null;
	}
}
