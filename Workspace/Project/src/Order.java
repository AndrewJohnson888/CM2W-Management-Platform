import java.util.ArrayList;

public class Order {

	private static int NEXT_ORDER_ID = 0;
	private String drink;
	private ArrayList<String> condiments;
	private String location;
	private int appID;
	private int orderID;

	Order(int appID, String drink, ArrayList<String> condiments, String location){
		
		this.orderID = Order.NEXT_ORDER_ID;
		Order.NEXT_ORDER_ID ++;
		
		this.appID = appID;
		this.drink = drink;
		this.condiments = condiments;
		this.location = location;
	}
	
	public int getAppID(){
		
		return this.appID;
	}
	
	public int getOrderID(){
		
		return this.orderID;
	}
	
	public String getDrink(){
		
		return this.drink;
	}
	
	public ArrayList<String> getCondiments(){
		
		return this.condiments;
	}
	
	public String getLocation(){
		
		return this.location;
	}
}
