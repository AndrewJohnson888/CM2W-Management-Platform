import java.util.ArrayList;
public class MobileOrderingApp implements AppObserver{
	
	private AppSubject subject;
	private ArrayList<Order> orders;
	private AppUI appUI;
	private int id;

	public MobileOrderingApp(AppSubject subject){
		
		this.orders = new ArrayList<Order>();
		
		this.appUI = new AppUI(this);
		
		this.subject = subject;
		this.subject.registerAppObserver(this);
	}
	
	public void createOrder(String drink, ArrayList<String> condiments, String location){
		
		Order order = new Order(this.id, drink, condiments, location);
		this.orders.add(order);
		this.appUI.printLine(order.printStatus());
		this.subject.addOrder(order);
	}

	@Override
	public void completeOrder(int orderID) {
		
		for (Order o : this.orders){
			
			if (o.checkID(orderID)){
				
				o.complete();
				this.appUI.setLine(o.printStatus(), o.getOrderIDString());
			}
		}
	}

	@Override
	public void registerApp(int appID, String[] drinks, String[] locations, String[] condiments) {
		
		this.id = appID;
		this.appUI.setOptions(drinks, locations, condiments);
	}
}
