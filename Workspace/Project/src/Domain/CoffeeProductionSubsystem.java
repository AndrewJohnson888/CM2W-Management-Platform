package Domain;
import java.util.ArrayList;
import java.util.HashMap;
import DataSource.CoffeeAndMachineData;
import DataSource.Order;
import Presentation.AppObserver;
import Presentation.Main;

public class CoffeeProductionSubsystem implements AppSubject, ControllerSubject{
	
	private static int NEXT_APP_ID = 0;
	private HashMap<Integer, AppObserver> appObservers;
	private HashMap<Integer, ControllerObserver> controllerObservers;
	private CoffeeAndMachineData data;

	public CoffeeProductionSubsystem(){		
		
		this.appObservers = new HashMap<Integer, AppObserver>();
		this.controllerObservers = new HashMap<Integer, ControllerObserver>();
		this.data = new CoffeeAndMachineData(this);
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
		
		for (int k : this.appObservers.keySet()){
			
			if (this.appObservers.get(k).equals(a)){
				
				this.appObservers.remove(k);
			}
		}
		
		return true;
	}
	
	@Override
	public boolean removeControllerObserver(ControllerObserver c) {

		this.controllerObservers.remove(c.registerID());
		
		return true;
	}

	@Override
	public void addOrder(Order o) {
		
		Main.simulateDelay();
		
		if (! this.verifyOrder(o)) {
			
			o.setStatusMessage("Invalid Order");
			this.updateOrder(o.getAppID(), o.getOrderIDInteger());
			return;
		}
		
		Main.simulateDelay();
		System.out.println("Verified order");
		
		int controllerID = this.data.getControllerIDAtLocation(o.getLocation());
		
		if (this.controllerObservers.containsKey(controllerID)){
			
			Main.simulateDelay();
			System.out.println("found controller");
			
			ControllerObserver c = this.controllerObservers.get(controllerID);
			
			if (c.checkAvailability()){
				
				Main.simulateDelay();
				System.out.println("found controller is available");
				
				ArrayList<String> recipe = this.data.getRecipe(o.getDrink());
				
				if (recipe == null){
					
					Main.simulateDelay();
					o.setStatusMessage("No Recipe Found");
					System.out.println("Ordered drink has no recipe");
					this.updateOrder(o.getAppID(), o.getOrderIDInteger());
					return;
				}
				
				o.addRecipe(recipe);
				c.addOrder(o);
				
				Main.simulateDelay();
				System.out.println("order has been added to controller job queue");
				return;
			}
				
			Main.simulateDelay();
			System.out.println("Controller is not available");
			
			o.setStatusMessage("Controller not Available");
			this.updateOrder(o.getAppID(), o.getOrderIDInteger());
			return;
		}
		
		Main.simulateDelay();
		System.out.println("There is no controller at the specified location");

		o.setStatusMessage("No controller at specified location");
		this.updateOrder(o.getAppID(), o.getOrderIDInteger());
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
	public boolean updateOrder(int appID, int orderID) {
		
		if (this.appObservers.containsKey(appID)){
			
			this.appObservers.get(appID).updateOrder(orderID);
			return true;
		}
		
		return false;
	}
}
