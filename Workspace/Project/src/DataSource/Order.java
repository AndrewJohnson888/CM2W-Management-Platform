	package DataSource;
import java.util.ArrayList;

public class Order {

	private static int NEXT_ORDER_ID = 0;
	private String drink;
	private ArrayList<String> condiments;
	private ArrayList<String> recipe;
	private String location;
	private int appID;
	private int id;
	private String status;

	public Order(int appID, String drink, ArrayList<String> condiments, String location){
		
		this.id = Order.NEXT_ORDER_ID;
		Order.NEXT_ORDER_ID ++;
		
		this.appID = appID;
		this.drink = drink;
		this.condiments = condiments;
		this.location = location;
		this.status = "processing";
	}
	
	public void setStatusMessage(String statusMessage){
		
		this.status = statusMessage;
	}
	
	public int getAppID(){
		
		return this.appID;
	}
	
	public String print(){
		
		String orderString = "";
		orderString += "Order " + String.format("%03d", this.id) + " status: " + this.status + "\n";
		orderString += "\t Drink: " + this.drink + "\n";
		orderString += "\t Condiments: ";
		for (String s : this.condiments) orderString += s + ", ";
		orderString += "\n";
		orderString += "\t location: " + this.location + "\n";
		orderString += "\n";
		
		return orderString;
	}
	
	public String getOrderIDString(){
		
		return String.format("%03d", this.id);
	}
	
	public int getOrderIDInteger(){
		
		return this.id;
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
	
	public void addRecipe(ArrayList<String> recipe){
		
		this.recipe = recipe;
	}
	
	public ArrayList<String> getRecipe(){
		
		return this.recipe;
	}
}
