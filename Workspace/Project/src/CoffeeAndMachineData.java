import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeAndMachineData {
	
	private ArrayList<String> condiments;
	private ArrayList<String> drinks;
	private ArrayList<String> locations;
	private HashMap<String, Integer> controllers;
	private HashMap<String, ArrayList<String>> reicpes;

	public CoffeeAndMachineData(){
		
		this.condiments = new ArrayList<String>();
		this.drinks = new ArrayList<String>();
		this.locations = new ArrayList<String>();
		this.controllers = new HashMap<String, Integer>();
		this.reicpes = new HashMap<String, ArrayList<String>>();	
		
		this.condiments.add("cream");
		this.condiments.add("sugar");
		this.condiments.add("nutrasweet");
		
		this.drinks.add("americano");
		this.drinks.add("latte");
		this.drinks.add("pumpkin spice");
		
		this.locations.add("200 N Main");
		this.locations.add("3 S Walnut");
		
		this.controllers.put("200 N Main", 0);
		this.controllers.put("3 S Walnut", 1);
		
		this.reicpes.put("americano", new ArrayList<String>());
		this.reicpes.get("americano").add("coffee");
		
		this.reicpes.put("latte", new ArrayList<String>());
		this.reicpes.get("latte").add("coffee");
		this.reicpes.get("latte").add("milk");
		this.reicpes.get("latte").add("whipped cream");
		this.reicpes.get("latte").add("sugar");
		
		this.reicpes.put("pumpkin spice", new ArrayList<String>());
		this.reicpes.get("pumpkin spice").add("coffee");
		this.reicpes.get("pumpkin spice").add("sugar");
		this.reicpes.get("pumpkin spice").add("pumpkin spice");
		
	}
	
	public boolean containsCondiment(String condiment){
		
		return this.condiments.contains(condiment);
	}
	
	public boolean containsDrink(String drink){
		
		return this.drinks.contains(drink);
	}
	
	public ArrayList<String> getRecipe(String drink){
		
		return this.reicpes.get(drink);
	}
	
	public boolean containsLocation(String location){
		
		return this.locations.contains(location);
	}
	
	public int getControllerIDAtLocation(String location){
		
		return this.controllers.get(location);
	}
}