package Domain;
import org.json.simple.JSONObject;

public abstract class Order {
	
	protected int orderID;
	protected int controllerID;
	protected String orderType;
	protected int status;
	protected String message;
	protected String drink;
	protected int coffeeMachineID;
	
	protected String constructResponse() {
		
		JSONObject jresponse = new JSONObject();
		jresponse.put("orderID", this.orderID);
		jresponse.put("coffee_machine_id", this.coffeeMachineID);
		jresponse.put("status", this.status);
		
		if (this.status == 0) jresponse.put("status-message", "Your coffee has been prepared with your desired options.");
		else {
			
			jresponse.put("status-message", "Your coffee order has been cancelled.");
			jresponse.put("error-message", this.message);
		}
		
		JSONObject jobject = new JSONObject();
		jobject.put("user-response", jresponse);
		
		return jobject.toJSONString();
	}

	protected boolean checkID(int id) {

		return this.orderID == id;
	}

	protected void updateStatus(int status, String message) {
		
		this.status = status;
		this.message = message;
	}
	
	protected int getControllerID() {

		return this.controllerID;
	}

	protected String getOrderType() {
	
		return this.orderType;
	}

	public abstract String constructCommand();
}
