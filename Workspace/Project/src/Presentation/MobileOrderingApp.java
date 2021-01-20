package Presentation;
import java.util.ArrayList;

import DataSource.Order;
import Domain.AppSubject;
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
		
		System.out.println("Send order to subsystem");
		String errorMessage = this.subject.addOrder(order);
		
		if (! errorMessage.isEmpty()){
			
			this.appUI.setLine(order.printError(errorMessage), order.getOrderIDString());
		}
	}

	@Override
	public void completeOrder(int orderID, String errorMessage) {
		
		for (Order o : this.orders){
			
			if (o.checkID(orderID)){
				
				if (! errorMessage.isEmpty()){
					
					this.appUI.setLine(o.printError(errorMessage), o.getOrderIDString());
					return;
				}
				
				o.complete();
				this.appUI.setLine(o.printStatus(), o.getOrderIDString());
			}
		}
		
		System.out.println("Completed order");
	}

	@Override
	public void registerApp(int appID, String[] drinks, String[] locations, String[] condiments) {
		
		this.id = appID;
		this.appUI.setOptions(drinks, locations, condiments);
	}
}
