package Domain;
import java.util.ArrayList;
import java.util.HashMap;

import DataSource.CoffeeAndMachineData;
import DataSource.Instruction;
import DataSource.Order;
import Presentation.AppObserver;

public class CoffeeProductionSubsystem implements AppSubject, ControllerSubject{
	
	private static int NEXT_APP_ID = 0;
	private static CoffeeProductionSubsystem cps;
	private HashMap<Integer, AppObserver> appObservers;
	private HashMap<Integer, ControllerObserver> controllerObservers;
	private CoffeeAndMachineData data;

	public CoffeeProductionSubsystem(){		
				
		CoffeeProductionSubsystem.cps = this;
		
		this.appObservers = new HashMap<Integer, AppObserver>();
		this.controllerObservers = new HashMap<Integer, ControllerObserver>();
		this.data = new CoffeeAndMachineData(this);
	}
	
	public static AppSubject connect(){
		
		return CoffeeProductionSubsystem.cps;
	}
	
	@Override
	public boolean registerAppObserver(AppObserver a) {
		
		a.registerApp(NEXT_APP_ID,
					  this.data.getDrinksArray(), 
					  this.data.getLocationsArray(), 
					  this.data.getCondimentsArray());

		this.appObservers.put(NEXT_APP_ID, a);
		NEXT_APP_ID ++;
		
		return true;
	}

	@Override
	public boolean registerControllerObserver(ControllerObserver c) {

		this.controllerObservers.put(c.registerID(), c);
		
		return true;
	}

	@Override
	public boolean removeAppObserver(AppObserver a) {
		
		return true;
	}
	
	@Override
	public boolean removControllerObserver(ControllerObserver c) {

		return true;
	}

	@Override
	public boolean notifyAppObserver(int appID) {

		return false;
	}

	@Override
	public String addOrder(Order o) {
		
		if (! this.verifyOrder(o)) return "Invalid Order";
		System.out.println("Verified order");
		
		int controllerID = this.data.getControllerIDAtLocation(o.getLocation());
		if (this.controllerObservers.containsKey(controllerID)){
			
			System.out.println("found controller");
			
			ControllerObserver c = this.controllerObservers.get(controllerID);
			
			if (c.checkAvailability()){
				
				System.out.println("found controller is available");
				
				ArrayList<String> recipe = this.data.getRecipe(o.getDrink());
				Instruction i = new Instruction(o.getAppID(), o.getOrderID(), o.getCondiments(), recipe);
				c.addInstruction(i);
				
				System.out.println("order has been added to controller job queue");
				
				return "";
			}
				
			return "Controller not Available";
		}

		return "No controller at specified location";
	}
	
	private boolean verifyOrder(Order o){
		
		if (! this.data.containsDrink(o.getDrink())) return false;
		if (! this.data.containsLocation(o.getLocation())) return false;

		for (String condiment : o.getCondiments()){
			
			if (! this.data.containsCondiment(condiment)) return false;
		}
		
		return true;
	}

	@Override
	public boolean completeOrder(int appID, int orderID, String errorMessage) {
		
		if (this.appObservers.containsKey(appID)){
			
			this.appObservers.get(appID).completeOrder(orderID, errorMessage);
			return true;
		}
		
		return false;
	}
}