package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This class is part of the strategy pattern which creates a command json string from an order.
 * 		This string will be sent to a controller to instruct it.
 * 		This class creates simple commands.
 *
 */
public class SimpleCommand extends CommandBehavior {
	
	private static final String SIMPLE_KEY = "Simple";

	@Override
	public String createCommand(int controllerID, int machineID, int orderID, String drink, HashMap<String, Integer> options, ArrayList<String> recipe) {
		
		JSONModule innerModule = new JSONModule();
		innerModule.put(CONTROLLER_ID_KEY, controllerID);
		innerModule.put(COFFEE_MACHINE_ID_KEY, machineID);
		innerModule.put(ORDER_ID_KEY, orderID);
		innerModule.put(DRINK_NAME_KEY, drink);
		innerModule.put(REQUEST_TYPE_KEY, SIMPLE_KEY);
		
		JSONModule outerModule = new JSONModule();
		outerModule.put(COMMAND_KEY, innerModule.toObject());
		
		return outerModule.toString();
	}
}
