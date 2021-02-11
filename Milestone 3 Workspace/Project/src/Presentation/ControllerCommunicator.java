package Presentation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author johnsoa8
 * Description: A class used for communication with controllers and storing their requests until the system control has time to deal with them.
 * 		This is where the queue of controller requests is stored.
 *
 */
public class ControllerCommunicator implements ControllerSubject{
	
	private static ControllerCommunicator uniqueInstance = new ControllerCommunicator();
	private static int NEXT_CONTROLLER_ID = 1;
	
	private Queue<String> responseQueue;
	private HashMap<Integer, ControllerObserver> controllers;

	private ControllerCommunicator(){
		
		this.responseQueue = new LinkedList<String>();
		this.controllers = new HashMap<Integer, ControllerObserver>();
	}
	
	public static ControllerCommunicator getInstance(){ return ControllerCommunicator.uniqueInstance; }
	
	public void addResponse(String response){
		
		this.responseQueue.add(response);
	}
	
	public String pollResponse(){
		
		return this.responseQueue.poll();
	}
	
	public void notifyControllerObserver(String command, int controllerID){
		
		this.controllers.get(controllerID).updateController(command);
	}

	public int registerControllerObserver(ControllerObserver controller) {

		this.controllers.put(NEXT_CONTROLLER_ID, controller);
		NEXT_CONTROLLER_ID ++;
		
		return NEXT_CONTROLLER_ID - 1;
	}

	public void removeControllerObserver(int controllerID) {
		
		this.controllers.remove(controllerID);
	}
}
