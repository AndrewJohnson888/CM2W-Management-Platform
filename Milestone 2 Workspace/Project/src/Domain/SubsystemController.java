package Domain;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import Presentation.AppObserver;
import Presentation.ControllerObserver;

public class SubsystemController implements ControllerSubject, AppSubject{
	
	private JSONModule jmodule;
	private ArrayList<ControllerObserver> controllers;
	private ArrayList<AppObserver> apps;
	private ArrayList<Order> orders;

	public SubsystemController(){
		
		this.jmodule = new JSONModule();
		this.controllers = new ArrayList<ControllerObserver>();
		this.apps = new ArrayList<AppObserver>();
		this.orders = new ArrayList<Order>();
	}
	
	public String addOrder(String jorder){
		
		try {
			
			Order order = this.jmodule.parseOrder(jorder);
			this.orders.add(order);
			return this.notifyControllerObserver(order);
		}
		
		catch (ParseException e) {

			System.out.println("ERROR: Invalid Order Format.");
		}
		
		return null;
	}
	
	public String drinkResponse(String jresponse){
		
		try {
			
			Response response = this.jmodule.parseResponse(jresponse);
			
			for (Order o : this.orders){
				
				if (o.checkID(response.getOrderID())){
					
					o.updateStatus(response.getStatus(), 
								   response.getMessage());
					
					
					return this.notifyAppObserver(o);
				}
			}
			
			System.out.println("Error: Order not found.");
			return null;
		} 
		
		catch (ParseException e) {

			System.out.println("ERROR: Invalid Response Format.");
		}
		
		return null;
	}

	@Override
	public void registerAppObserver(AppObserver a) {
		
		this.apps.add(a);
	}

	@Override
	public void removeAppObserver(AppObserver a) {
		
		this.apps.remove(a);
	}

	@Override
	//would normally send data to identified controller, but will print out instead for testing
	public String notifyAppObserver(Order o) {
		
		String jresponse = o.constructResponse();
		return jresponse;
	}

	@Override
	public void registerControllerObserver(ControllerObserver c) {
		
		this.controllers.add(c);
	}

	@Override
	public void removeControllerObserver(ControllerObserver c) {
		
		this.controllers.remove(c);
	}

	@Override
	//would normally send data to identified controller, but will print out instead for testing
	public String notifyControllerObserver(Order o) {
		
		String jcommand = o.constructCommand();
		return jcommand;
	}
}
