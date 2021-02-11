package Testing;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import Domain.SystemControl;
import Presentation.App;
import Presentation.AppCommunicator;
import Presentation.Controller;
import Presentation.ControllerCommunicator;

/**
 * 
 * @author johnsoa8
 * Description: test cases for the functionality of the system under all of the different order types. 
 *
 */
public class FunctionalityTest {

	@Test
	public void funtionalityTest() throws InterruptedException, ParseException {
		
		// Simple Test 
		String order1 = "{\"order\": { \"orderID\": 2,\"address\": {\"street\": \"200 N Main\",\"ZIP\": 47803},\"drink\": \"Expresso\"}}";
		String command1 = "{\"command\": {\"controller_id\": 1,\"coffee_machine_id\": 2,\"orderID\": 2,\"DrinkName\": \"Expresso\",\"Requesttype\": \"Simple\"}}";
		String drinkResponse1 = "{\"drinkresponse\": {\"orderID\": 2,\"status\": 1,\"errordesc\": \"Out of milk, drink cancelled.\",\"errorcode\": 2}}";
		String userResponse1 = "{\"user-response\": {\"orderID\": 2,\"coffee_machine_id\": 2,\"status\": 1,\"status-message\": \"Your coffee order has been cancelled.\",\"error-message\": \"Out of milk, drink cancelled.\"}}";
		
		// Automated Test
		String order2 = "{\"order\": { \"orderID\": 1,\"address\": {\"street\": \"200 N Main\",\"ZIP\": 47803},\"drink\": \"Americano\",\"condiments\": [{\"name\": \"Sugar\", \"qty\": 1},{\"name\": \"Cream\", \"qty\": 2}]}}";	
		String command2 = "{\"command\": {\"controller_id\": 1,\"coffee_machine_id\": 1,\"orderID\": 1,\"DrinkName\": \"Americano\",\"Requesttype\": \"Automated\",\"Options:\": [{\"Name\": \"Cream\", \"qty\": 2},{\"Name\": \"Sugar\", \"qty\": 1}]}}";
		String drinkResponse2 = "{\"drinkresponse\": {\"orderID\": 1,\"status\": 0}}";
		String userResponse2 = "{\"user-response\": {\"orderID\": 1,\"coffee_machine_id\": 1,\"status\": 0,\"status-message\": \"Your coffee has been prepared with your desired options.\"}}";
		
		// Programmable Test
		String order3 = "{\"order\": { \"orderID\": 3,\"address\": {\"street\": \"200 N Main\",\"ZIP\": 47803},\"drink\": \"Pumpkin Spice\",\"condiments\": [{\"name\": \"Sugar\", \"qty\": 2}]}}";
		String command3 = "{\"command\": {\"controller_id\": 1,\"coffee_machine_id\": 3,\"orderID\": 3,\"DrinkName\": \"Pumpkin Spice\",\"Requesttype\": \"Programmable\",\"Options:\": [{\"Name\": \"Sugar\", \"qty\": 2} ],\"Recipe\": [{\"commandstep\": \"add\", \"object\": \"Coffee\"},{\"commandstep\": \"add\", \"object\": \"Pumpkin Spice\"},{\"commandstep\": \"add\", \"object\": \"Cream\"},{\"commandstep\": \"mix\"},{\"commandstep\": \"add\", \"object\": \"Nutmeg\"}]}}";
		String drinkResponse3 = "{\"drinkresponse\": {\"orderID\": 3,\"status\": 1,\"errordesc\": \"Machine jammed.\",\"errorcode\": 26}}";
		String userResponse3 = "{\"user-response\": {\"orderID\": 3,\"coffee_machine_id\": 3,\"status\": 1,\"status-message\": \"Your coffee order has been cancelled.\",\"error-message\": \"Machine jammed.\"}}";
		
		
		SystemControl sc = new SystemControl();
		AppCommunicator ac = AppCommunicator.getInstance();
		ControllerCommunicator cc = ControllerCommunicator.getInstance();
		
		App app1 = new App(ac);
		Controller controller1 = new Controller(cc);
		
		app1.sendOrder(order1);
		Thread.sleep(2000); // wait for order to be fulfilled
		TestingUtilities.compareCommandJSON(controller1.getLastCommand(), command1, "Simple");
		controller1.sendResponse(drinkResponse1);
		Thread.sleep(2000); // wait for response to be processed
		TestingUtilities.compareResponseJSON(app1.getLastResponse(), userResponse1);
		
		app1.sendOrder(order2);
		Thread.sleep(2000); // wait for order to be fulfilled
		TestingUtilities.compareCommandJSON(controller1.getLastCommand(), command2, "Automated");
		controller1.sendResponse(drinkResponse2);
		Thread.sleep(2000); // wait for response to be processed
		TestingUtilities.compareResponseJSON(app1.getLastResponse(), userResponse2);
		
		app1.sendOrder(order3);
		Thread.sleep(2000); // wait for order to be fulfilled
		TestingUtilities.compareCommandJSON(controller1.getLastCommand(), command3, "Programmable");
		controller1.sendResponse(drinkResponse3);
		Thread.sleep(2000); // wait for response to be processed
		TestingUtilities.compareResponseJSON(app1.getLastResponse(), userResponse3);
		
		sc.shutdown();
	}
}
