import java.util.ArrayList;
import java.util.Scanner;

public class MobileOrderingApp implements AppObserver{
	
	private static int NEXT_APP_ID = 0;
	private int appID;
	private Scanner scanner;
	private AppSubject subject;
	private ArrayList<Order> orders;
	private AppUI appUI;

	public MobileOrderingApp(AppSubject subject){
		
		this.subject = subject;
		this.subject.registerAppObserver(this);
		
		this.orders = new ArrayList<Order>();
		
		this.appID = MobileOrderingApp.NEXT_APP_ID;
		MobileOrderingApp.NEXT_APP_ID ++;
		
		this.scanner = new Scanner(System.in);
		
		this.appUI = new AppUI(this);
	}
	
	public void createOrder(String drink, ArrayList<String> condiments, String location){
		
		Order order = new Order(this.appID, drink, condiments, location);
		this.orders.add(order);
		this.appUI.printLine(order.printStatus());
		this.subject.addOrder(order);
	}

	@Override
	public boolean checkID(int ID) {
		
		return this.appID == ID;
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
}
