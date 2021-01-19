import java.util.ArrayList;

public class CoffeeProductionSubsystem implements AppSubject, ControllerSubject{
	
	private static CoffeeProductionSubsystem cps;
	private ArrayList<AppObserver> appObservers;
	private ArrayList<ControllerObserver> controllerObservers;
	private CoffeeAndMachineData data;

	public CoffeeProductionSubsystem(){		
				
		CoffeeProductionSubsystem.cps = this;
		
		this.appObservers = new ArrayList<AppObserver>();
		this.controllerObservers = new ArrayList<ControllerObserver>();
		this.data = new CoffeeAndMachineData();
	}
	
	public static AppSubject connect(){
		
		return CoffeeProductionSubsystem.cps;
	}
	
	@Override
	public boolean registerAppObserver(AppObserver a) {

		this.appObservers.add(a);
		
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
		
		for (AppObserver a : this.appObservers){
			
			if (a.checkID(appID)){
				
				a.completeOrder(orderID);
			}
		}
		
		return false;
	}
}