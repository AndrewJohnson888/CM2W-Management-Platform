import java.util.ArrayList;
import java.util.Scanner;

public class MobileOrderingApp implements AppObserver{
	
	private static int NEXT_APP_ID = 0;
	private int appID;
	private Scanner scanner;
	private AppSubject subject;
	private ArrayList<Order> orders;

	public MobileOrderingApp(AppSubject subject){
		
		this.subject = subject;
		this.subject.registerAppObserver(this);
		
		this.orders = new ArrayList<Order>();
		
		this.appID = MobileOrderingApp.NEXT_APP_ID;
		MobileOrderingApp.NEXT_APP_ID ++;
		
		this.scanner = new Scanner(System.in);
	
		getUserCommand();
	}
	
	private void getUserCommand(){
		
		while (true){
		
			System.out.println("Enter Command: ");
			System.out.print("->");
			String command = this.scanner.nextLine();
			System.out.println();
			
			switch(command){
			
				case "exit":
					System.exit(0);
					break;
			
				case "create order":
					createOrder();
					break;
					
				default:
					System.out.println("Command not recognized");
					break;
			}
		}
	}
	
	public void createOrder(){
	
		ArrayList<String> condiments = new ArrayList<String>();
		
		System.out.println("Create an Order");
		
		System.out.println("Drink: ");
		System.out.print("->");
		String drink = this.scanner.nextLine();
		
		System.out.println("Condiments: ");
		System.out.print("->");
		String condimentsString = this.scanner.nextLine();
		for (String c : condimentsString.split(" ")) condiments.add(c);
		
		System.out.println("Location: ");
		System.out.print("->");
		String location = this.scanner.nextLine();
		
		System.out.println("Your order has been placed");
		System.out.println();
		
		Order order = new Order(this.appID, drink, condiments, location);
		this.orders.add(order);
		this.subject.addOrder(order);
	}

	@Override
	public boolean checkID(int ID) {
		
		return this.appID == ID;
	}

	@Override
	public void completeOrder(int ID) {
		
		System.out.println("Order with ID: " + ID + " has completed");
		System.out.println();
	}
}
