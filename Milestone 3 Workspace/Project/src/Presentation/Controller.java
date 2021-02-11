package Presentation;

import InteractiveDemo.InteractiveDemo;

/**
 * 
 * @author johnsoa8
 * Description: A dummy class used for testing and demonstration purposes. Although, it also shows how a real controller might 
 * 		interact with the system. 
 *
 */
public class Controller implements ControllerObserver{
	
	private int controllerID;
	private ControllerSubject subject;
	private String lastCommand;
	
	public Controller(ControllerSubject subject){
		
		this.subject = subject;
		this.controllerID = subject.registerControllerObserver(this);
		this.lastCommand = null;
	}

	public void updateController(String command) {
	
		if (InteractiveDemo.VERBOSE) System.out.println("Controller " + this.controllerID + " recieved command: " + command);
		this.lastCommand = command;
	}
	
	public void sendResponse(String response){
		
		this.subject.addResponse(response);
	}
	
	public String getLastCommand(){
		
		return this.lastCommand;
	}
}