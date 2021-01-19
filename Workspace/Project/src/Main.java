
public class Main {

	public static void main(String args[]){
		
		CoffeeProductionSubsystem cps = new CoffeeProductionSubsystem();
		
		new SimpleController(cps);
		new AdvancedController();
		
		new MobileOrderingApp(cps);
		new MobileOrderingApp(cps);
	}
}
