package Presentation;

import InteractiveDemo.InteractiveDemo;

/**
 * 
 * @author johnsoa8
 * Description: A dummy class used for testing and demonstration purposes. Although, it also shows how a real app might 
 * 		interact with the system. 
 *
 */
public class App implements AppObserver {
	
	private int appID;
	private AppSubject subject;
	private String lastResponse;
	
	public App(AppSubject subject){
		
		this.subject = subject;
		this.appID = subject.registerAppObserver(this);
		this.lastResponse = null;
	}

	public void updateApp(String response) {
		
		if (InteractiveDemo.VERBOSE) System.out.println("App " + this.appID + " recieved response: " + response);
		this.lastResponse = response;
	}
	
	public void sendOrder(String order){
		
		this.subject.addOrder(order, this.appID);
	}
	
	public String getLastResponse(){
		
		return this.lastResponse;
	}
}
