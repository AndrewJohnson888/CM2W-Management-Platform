package Presentation;
import java.util.ArrayList;
import java.util.HashMap;
import DataSource.Order;
import Domain.AppSubject;

public class MobileOrderingApp implements AppObserver{
	
	private AppSubject subject;
	private HashMap<Integer, Order> orders;
	private AppUI appUI;
	private int id;

	public MobileOrderingApp(AppSubject subject){
		
		this.orders = new HashMap<Integer, Order>();
		
		this.appUI = new AppUI(this);
		
		this.subject = subject;
		this.subject.registerAppObserver(this);
	}
	
	public void createOrder(String drink, ArrayList<String> condiments, String location){
		
		Order order = new Order(this.id, drink, condiments, location);
		this.orders.put(order.getOrderIDInteger(), order);
		this.appUI.printLine(order.print());
		
		System.out.println();
		System.out.println("Sent order to subsystem");
		this.subject.addOrder(order);
	}

	@Override
	public void updateOrder(int orderID) {
		
		if (this.orders.containsKey(orderID)){
			
			Order o = this.orders.get(orderID);
			this.appUI.setLine(o.print(), o.getOrderIDString());
		}
	}

	@Override
	public void registerApp(int appID, String[] drinks, String[] locations, String[] condiments) {
		
		this.id = appID;
		this.appUI.setOptions(drinks, locations, condiments);
	}
}
