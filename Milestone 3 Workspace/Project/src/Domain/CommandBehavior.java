package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This class is part of the strategy pattern which creates a command json string from an order.
 * 		There will be a class that implements this one for each type of controller (simple, automated, etc.)
 * 		It also contains keywords which are used in the json file. If the json keywords should change, they can be changed here
 * 		in one centralized place.
 *
 */
public abstract class CommandBehavior {
	
	protected final static String CONTROLLER_ID_KEY = "controller_id";
	protected final static String COFFEE_MACHINE_ID_KEY = "coffee_machine_id";
	protected final static String ORDER_ID_KEY = "orderID";
	protected final static String DRINK_NAME_KEY = "DrinkName";
	protected final static String REQUEST_TYPE_KEY = "Requesttype";
	protected final static String COMMAND_KEY = "command";
	protected final static String NAME_KEY = "Name";
	protected final static String QUANTITY_KEY = "qty";
	protected final static String OPTIONS_KEY = "Options:";
	protected final static String COMMAND_STEP_KEY = "commandstep";
	protected final static String ADD_KEY = "add";
	protected final static String RECIPE_KEY = "Recipe";
	protected final static String OBJECT_KEY = "object";
	
	public abstract String createCommand(int controllerID, 
										 int machineID, 
										 int orderID, 
										 String drink, 
										 HashMap<String, Integer> options, 
										 ArrayList<String> recipe);
}