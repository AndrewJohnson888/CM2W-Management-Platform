package DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Domain.CoffeeProductionSubsystem;
import Domain.SimpleController;

public class CoffeeAndMachineData {
	
	private final String databaseString = "src/Database/";
	private final String capabilityFile = "Capability";
	private final String coffeeMakerFile = "CoffeeMaker";
	private final String coffeeMakerCapabilityFile = "CoffeeMakerCapability";
	private final String coffeeMakerDrinkFile = "CoffeeMakerDrink";
	private final String condimentFile = "Condiment";
	private final String controllerFile = "Controller";
	private final String drinkIngredientFile = "DrinkIngredient";
	private final String drinkTypesFile = "DrinkTypes";
	private final String ingredientFile = "Ingredient";

	public CoffeeAndMachineData(CoffeeProductionSubsystem cps){
		
		HashMap<String, ArrayList<String>> controllerTable = this.getControllerTable();
		
		for (String k : controllerTable.keySet()){
			
			switch (controllerTable.get(k).get(0)){
			
				case "Simple":
					new SimpleController(cps, 
										 Integer.parseInt(k), 
										 Boolean.parseBoolean(controllerTable.get(k).get(3)), 
										 Boolean.parseBoolean(controllerTable.get(k).get(4)));
					break;
//				case "Advanced": 
//					new AdvancedController(cps, Integer.parseInt(k));
//					break;
			}
		}
	}
	
	private HashMap<String, ArrayList<String>> getCapabilityTable(){
		
		return this.readTable(this.capabilityFile);
	}
	
	private HashMap<String, ArrayList<String>> getCoffeeMakerTable(){
		
		return this.readTable(this.coffeeMakerFile);
	}
	
	private HashMap<String, ArrayList<String>> getCoffeeMakerCapabilityTable(){
		
		return this.readTable(this.coffeeMakerCapabilityFile);
	}
	
	private HashMap<String, ArrayList<String>> getCoffeeMakerDrinkTable(){
		
		return this.readTable(this.coffeeMakerDrinkFile);
	}
	
	private HashMap<String, ArrayList<String>> getCondimentTable(){
		
		return this.readTable(this.condimentFile);
	}
	
	private HashMap<String, ArrayList<String>> getControllerTable(){
		
		return this.readTable(this.controllerFile);
	}
	
	private ArrayList<String> getLocations(){
		
		ArrayList<String> locations = new ArrayList<String>();
		
		for (ArrayList<String> a : this.getControllerTable().values()){
			
			locations.add(a.get(1));
		}
		
		return locations;
	}
	
	private HashMap<String, ArrayList<String>> getDrinkIngredientTable(){
		
		return this.readTable(this.drinkIngredientFile);
	}
	
	private HashMap<String, ArrayList<String>> getDrinkTypesTable(){
		
		return this.readTable(this.drinkTypesFile);
	}
	
	private HashMap<String, ArrayList<String>> getIngredientTable(){
		
		return this.readTable(this.ingredientFile);
	}
	
	private HashMap<String, ArrayList<String>> readTable(String filename){
		
		try {
			
			HashMap<String, ArrayList<String>> table = new HashMap<String, ArrayList<String>>();
			
			Scanner scanner = new Scanner(new File(this.databaseString + filename));

			String name;
			String description;
			
			while (scanner.hasNext()){
				
				name = scanner.nextLine();

				description = scanner.nextLine();
				ArrayList<String> fields = new ArrayList<String>();
				while(!description.trim().isEmpty()){
					
					fields.add(description);
					
					if (!scanner.hasNext()) break;
					
					description = scanner.nextLine();
				}
				
				table.put(name, fields);
			}
			
			scanner.close();
			
			return table;
		} 
		
		catch (FileNotFoundException e) {
			
			System.out.println("ERROR: " + filename + " Table was not found.");
		}
		
		return null;
	}
	
	public String[] getDrinksArray(){
		
		return this.getDrinkTypesTable().keySet().toArray(new String[0]);
	}
	
	public String[] getLocationsArray(){
		
		return this.getLocations().toArray(new String[0]);
	}
	
	public String[] getCondimentsArray(){
		
		return (String[]) this.getCondimentTable().keySet().toArray(new String[0]);
	}
	
	public boolean containsCondiment(String condiment){
		
		return this.getCondimentTable().containsKey(condiment);
	}
	
	public boolean containsDrink(String drink){
		
		return this.getDrinkTypesTable().containsKey(drink);
	}
	
	public boolean containsLocation(String location){
		
		return this.getLocations().contains(location);
	}
	
	public int getControllerIDAtLocation(String location){
		
		HashMap<String, ArrayList<String>> controllerTable = this.getControllerTable();
		
		for (String k : controllerTable.keySet()){
			
			if (controllerTable.get(k).get(1).equals(location)){
				
				return Integer.parseInt(k);
			}
		}
		
		return -1;
	}
	
	public ArrayList<String> getRecipe(String drink){
		
		return this.getDrinkIngredientTable().get(drink);
	}
}