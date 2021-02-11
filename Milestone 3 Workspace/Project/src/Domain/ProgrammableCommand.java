package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This class is part of the strategy pattern which creates a command json string from an order.
 * 		This string will be sent to a controller to instruct it.
 * 		This class creates programmable commands.
 *
 */
public class ProgrammableCommand extends CommandBehavior {
	
	private static final String PROGRAMMABLE_KEY = "Programmable";

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
		
		JSONModule recipeModule = new JSONModule();
		
		for (String s : recipe){
			
			itemModule = new JSONModule();
			
			if (s.toUpperCase().charAt(0) == s.charAt(0)){
				
				itemModule.put(COMMAND_STEP_KEY, ADD_KEY);
				itemModule.put(OBJECT_KEY, s);
			}
			
			else {
				
				itemModule.put(COMMAND_STEP_KEY, s);
			}
			
			recipeModule.add(itemModule.toObject());
		}
		
		JSONModule innerModule = new JSONModule();
		innerModule.put(CONTROLLER_ID_KEY, controllerID);
		innerModule.put(COFFEE_MACHINE_ID_KEY, machineID);
		innerModule.put(ORDER_ID_KEY, orderID);
		innerModule.put(DRINK_NAME_KEY, drink);
		innerModule.put(REQUEST_TYPE_KEY, PROGRAMMABLE_KEY);
		innerModule.put(OPTIONS_KEY, optionsModule.toObject());
		innerModule.put(RECIPE_KEY, recipeModule.toObject());
		
		JSONModule outerModule = new JSONModule();
		outerModule.put(COMMAND_KEY, innerModule.toObject());
		
		return outerModule.toString();
	}
}
