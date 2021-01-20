import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeProductionSubsystem implements AppSubject, ControllerSubject{
	
	private static int NEXT_APP_ID = 0;
	private static CoffeeProductionSubsystem cps;
	private HashMap<Integer, AppObserver> appObservers;
	private ArrayList<ControllerObserver> controllerObservers;
	private CoffeeAndMachineData data;

	public CoffeeProductionSubsystem(){		
				
		CoffeeProductionSubsystem.cps = this;
		
		this.appObservers = new HashMap<Integer, AppObserver>();
		this.controllerObservers = new ArrayList<ControllerObserver>();
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
	public boolean registerControllerObserver(ControllerObserver a) {

		this.controllerObservers.add(a);
		
		return true;
	}

	@Override
	public boolean removeAppObserver(AppObserver a) {

		return false;
	}

	@Override
	public boolean notifyAppObserver(int appID) {

		return false;
	}

	@Override
	public boolean addOrder(Order o) {
		
		if (! this.verifyOrder(o)) return false;
		
		int controllerID = this.data.getControllerIDAtLocation(o.getLocation());
		for (ControllerObserver c : this.controllerObservers){
			
			if (c.checkID(controllerID)){
				
				if (c.checkAvailability()){
					
					ArrayList<String> recipe = this.data.getRecipe(o.getDrink());
					Instruction i = new Instruction(o.getAppID(), o.getOrderID(), o.getCondiments(), recipe);
					c.addInstruction(i);
				}
			}
		}

		return false;
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
	public boolean completeOrder(int appID, int orderID) {
		
		if (this.appObservers.containsKey(appID)){
			
			this.appObservers.get(appID).completeOrder(orderID);
			return true;
		}
		
		return false;
	}
}
