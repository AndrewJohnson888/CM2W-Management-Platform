package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This class is part of the strategy pattern which creates a command json string from an order.
 * 		This string will be sent to a controller to instruct it.
 * 		This class creates automated commands.
 *
 */
public class AutomatedCommand extends CommandBehavior {
	
	private static final String AUTOMATED_KEY = "Automated";

	@Override
	public String createCommand(int controllerID, int machineID, int orderID, String drink, HashMap<String, Integer> options, ArrayList<String> recipe) {
		
		JSONModule optionsModule = new JSONModule();
		
		JSONModule itemModule;
		for (String s : options.keySet()){
			
			itemModule = new JSONModule();
			itemModule.put(NAME_KEY, s);
			itemModule.put(QUANTITY_KEY, options.get(s));
			optionsModule.add(itemModule.toObject());
		}
		
		JSONModule innerModule = new JSONModule();
		innerModule.put(CONTROLLER_ID_KEY, controllerID);
		innerModule.put(COFFEE_MACHINE_ID_KEY, machineID);
		innerModule.put(ORDER_ID_KEY, orderID);
		innerModule.put(DRINK_NAME_KEY, drink);
		innerModule.put(REQUEST_TYPE_KEY, AUTOMATED_KEY);
		innerModule.put(OPTIONS_KEY, optionsModule.toObject());
		
		JSONModule outerModule = new JSONModule();
		outerModule.put(COMMAND_KEY, innerModule.toObject());
		
		return outerModule.toString();
	}
}
