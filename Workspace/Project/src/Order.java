import java.util.ArrayList;

public class Order {

	private static int NEXT_ORDER_ID = 0;
	private String drink;
	private ArrayList<String> condiments;
	private String location;
	private int appID;
	private int orderID;
	private String status;

	Order(int appID, String drink, ArrayList<String> condiments, String location){
		
		this.orderID = Order.NEXT_ORDER_ID;
		Order.NEXT_ORDER_ID ++;
		
		this.appID = appID;
		this.drink = drink;
		this.condiments = condiments;
		this.location = location;
		this.status = "processing";
	}
	
	public int getAppID(){
		
		return this.appID;
	}
	
	public String printStatus(){
		
		return "Status of order " + String.format("%05d", this.orderID) + ": " + this.status;
	}
	
	public int getOrderID(){
		
		return this.orderID;
	}
	
	public void complete(){
		
		this.status = "complete";
	}
	
	public String getOrderIDString(){
		
		return String.format("%05d", this.orderID);
	}
	
	public boolean checkID(int ID){
		
		return this.orderID == ID;
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
