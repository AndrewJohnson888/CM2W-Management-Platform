package Domain;

/**
 * 
 * @author johnsoa8
 * Description: An order holds all of the relevant information to an actual coffee order. It has methods to parse and generate a response
 * 		from a controller (using a JSONModule) since these responses don't chagne based on controller type.
 * 		What does change is the controller command, which is dealt with using the command pattern and delegating to 
 * 		specific Order subclasses. 
 *
 */
public abstract class Order {

	private static final String SUCCEEDED_MESSAGE = "Your coffee has been prepared with your desired options.";
	private static final String FAILED_MESSAGE = "Your coffee order has been cancelled.";
	
	protected CommandBehavior behavior;
	protected Coffee drink;
	protected int controllerID;
	protected int machineID;
	protected int appID;
	protected int orderID;
	
	private int status = 0;
	private String statusMessage = SUCCEEDED_MESSAGE;
	private String errorMessage = null;
	
	public String createResponse() {	
		
		JSONModule innerModule = new JSONModule();
		innerModule.put("orderID", this.orderID);
		innerModule.put("coffee_machine_id", this.machineID);
		innerModule.put("status", this.status);
		innerModule.put("status-message", this.statusMessage);
		if (this.status != 0) innerModule.put("error-message", this.errorMessage);
		
		JSONModule outerModule = new JSONModule();
		outerModule.put("user-response", innerModule.toObject());
		
		return outerModule.toString();
	}

	public void parseResponse(String jresponse) {
		
		JSONModule jmodule = new JSONModule();
		jmodule.parseResponse(jresponse);
		
		this.status = jmodule.getStatus();
		
		if (this.status != 0){
			
			this.errorMessage = jmodule.getErrorDescription();
			this.statusMessage = FAILED_MESSAGE;
		}
	}

	public boolean checkID(int id) { return this.orderID == id; }
	public int getAppID() { return this.appID; };
	public int getControllerID() { return this.controllerID; };

	public abstract String createCommand();
}
