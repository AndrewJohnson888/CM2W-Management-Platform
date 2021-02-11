package InteractiveDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import Domain.JSONModule;
import Domain.SystemControl;
import Presentation.App;
import Presentation.AppCommunicator;
import Presentation.Controller;
import Presentation.ControllerCommunicator;

/**
 * 
 * @author johnsoa8
 * Description: A poorly written demo class that can be used to checkout the system. This demo was not built very robustly as
 * 		it was a last minute addition. Expect that it will break in some scenarios.
 * 		In particular, ensure you type everything correctly and leave no leading/trailing spaces. 
 * 		Change the CONTROLLER_RESPONSE_TIME in order to allow you to enter multiple orders at once without them finishing before you
 * 		get to order the next one. This shows that the queueing process works and the system can handle concurrent orders. 
 *
 */
public class InteractiveDemo {
	
	public static final boolean VERBOSE = true;

	public static void main(String[] args){
		
		int CONTROLLER_RESPONSE_TIME = 5000;
		
		Scanner scanner = new Scanner(System.in);
		
		SystemControl sc = new SystemControl();
		
		ArrayList<App> apps = new ArrayList<App>();
		HashMap<String, Integer> condiments = new HashMap<String, Integer>();
		
		Controller controller1 = new Controller(ControllerCommunicator.getInstance());
		Controller controller2 = new Controller(ControllerCommunicator.getInstance());

		int orderID = 1;
		int appID = 1;
		
		int zip; 
		String drink;
		String name; 
		int quantity;
		
		while(true){
			
			condiments.clear();
		
			System.out.println();
			System.out.println("Enter a command");
			System.out.println("Options: make app, order, shutdown");
			String command = scanner.nextLine();
			
			if (command.equals("shutdown")) {
				
				System.out.println("Shutting Down...");
				sc.shutdown();
				break;
			}
			
			else if (command.equals("make app")){

				System.out.println("A new app has been made with id: " + appID);
				App app = new App(AppCommunicator.getInstance());
				apps.add(app);
				appID ++;
			}
			
			else if (command.equals("order")){
				
				System.out.println("Enter street: ");
				String street = scanner.nextLine();
				
				System.out.println("Enter zip: ");
				zip = Integer.parseInt(scanner.nextLine());
				
				System.out.println("Enter drink: ");
				drink = scanner.nextLine();
				
				while(true){
				
					System.out.println("Add condiment? (Y/N)");
					String resp = scanner.nextLine();
					
					if (! resp.equals("Y")) break;
					
					System.out.println("Enter condiment name: ");
					name = scanner.nextLine();

					System.out.println("Enter condiment quantity: ");
					quantity = Integer.parseInt(scanner.nextLine());
					
					condiments.put(name, quantity);
				}
				
				System.out.println("Send from which app: ");
				int id = Integer.parseInt(scanner.nextLine());
				
				JSONModule addressModule = new JSONModule();
				addressModule.put("street", street);
				addressModule.put("ZIP", zip);
				
				JSONModule condimentsModule = new JSONModule();
				
				if (condiments.size() > 0){
				
					JSONModule itemModule;
					for (String key : condiments.keySet()){
						
						itemModule = new JSONModule();
						itemModule.put("name", key);
						itemModule.put("qty", condiments.get(key));
						condimentsModule.add(itemModule.toObject());
					}
				}
				
				JSONModule innerModule = new JSONModule();
				innerModule.put("orderID", orderID);
				innerModule.put("drink", drink);
				innerModule.put("address", addressModule.toObject());
				if (condiments.size() > 0) innerModule.put("condiments", condimentsModule.toObject());
				
				JSONModule outerModule = new JSONModule();
				outerModule.put("order", innerModule.toObject());
				
				apps.get(id - 1).sendOrder(outerModule.toString());
				
				System.out.println("App " + id + " has sent order " + orderID);
				
				final int finalOrderID = orderID;
				
				if (zip == 47803){
				
					Timer controllerTimer = new Timer();
					controllerTimer.schedule(new TimerTask() {
						
						public void run(){
							
							controller1.sendResponse("{\"drinkresponse\": {\"orderID\": " + finalOrderID + ",\"status\": 0}}");
							controllerTimer.cancel();
						}
					}, CONTROLLER_RESPONSE_TIME, CONTROLLER_RESPONSE_TIME);
				}
				
				else {
					
					Timer controllerTimer = new Timer();
					controllerTimer.schedule(new TimerTask() {
						
						public void run(){
							
							controller2.sendResponse("{\"drinkresponse\": {\"orderID\": " + finalOrderID + ",\"status\": 0}}");
							controllerTimer.cancel();
						}
					}, CONTROLLER_RESPONSE_TIME, CONTROLLER_RESPONSE_TIME);
				}
				
				orderID ++;
			}
			
			else {
				
				System.out.println("Command not recognized");
			}
		}
		
		System.out.println("Program Terminated");
	}
}