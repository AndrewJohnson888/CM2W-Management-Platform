package DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PersistenceInterface {

	private final String databaseString = "src/DataSource/";
	private final String capabilityFile = "Capability";
	private final String coffeeMakerFile = "CoffeeMaker";
	private final String coffeeMakerCapabilityFile = "CoffeeMakerCapability";
	private final String coffeeMakerDrinkFile = "CoffeeMakerDrink";
	private final String condimentFile = "Condiment";
	private final String controllerFile = "Controller";
	private final String drinkIngredientFile = "DrinkIngredient";
	private final String drinkTypesFile = "DrinkTypes";
	private final String ingredientFile = "Ingredient";
	
	public HashMap<String, ArrayList<String>> getCapabilityTable(){
		
		return this.readTable(this.capabilityFile);
	}
	
	public HashMap<String, ArrayList<String>> getCoffeeMakerTable(){
		
		return this.readTable(this.coffeeMakerFile);
	}
	
	public HashMap<String, ArrayList<String>> getCoffeeMakerCapabilityTable(){
		
		return this.readTable(this.coffeeMakerCapabilityFile);
	}
	
	public HashMap<String, ArrayList<String>> getCoffeeMakerDrinkTable(){
		
		return this.readTable(this.coffeeMakerDrinkFile);
	}
	
	public HashMap<String, ArrayList<String>> getCondimentTable(){
		
		return this.readTable(this.condimentFile);
	}
	
	public HashMap<String, ArrayList<String>> getControllerTable(){
		
		return this.readTable(this.controllerFile);
	}
	
	public HashMap<String, ArrayList<String>> getDrinkIngredientTable(){
		
		return this.readTable(this.drinkIngredientFile);
	}
	
	public HashMap<String, ArrayList<String>> getDrinkTypesTable(){
		
		return this.readTable(this.drinkTypesFile);
	}
	
	public HashMap<String, ArrayList<String>> getIngredientTable(){
		
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
}
