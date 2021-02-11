package DataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Domain.MachineType;

/**
 * 
 * @author johnsoa8
 * Description: This class is used as a gateway to the database (or whatever storage device is being used)
 * 		This way, no other class need worry about the low level details of interacting with the database. 
 * 		If the database changes, this is the only class that would need to chagne as well.
 * 		Currently, the "database" is a bunch of text files that are read by this class. (see the Persistence package)
 *
 */
public class PersistenceInterface {
	
	private final String databaseString = "src/Persistence/";
	private final String coffeeMakerFile = "CoffeeMaker";
	private final String condimentFile = "Condiment";
	private final String controllerFile = "Controller";
	private final String drinkTypesFile = "DrinkTypes";
	private final String ingredientFile = "Ingredient";
	
	public ArrayList<String> getRecipe(String drink){
		
		HashMap<String, ArrayList<String>> drinkTypes = this.getDrinkTypesTable();
		
		if (drinkTypes.containsKey(drink)){
			
			ArrayList<String> recipe = drinkTypes.get(drink);
			
			if (recipe.size() == 0) return null;
			return recipe;
		}
		
		System.out.println("ERROR: Persistence does not contain specified drink.");
		return null;
	}
	
	public String[] findMachine(MachineType machineType, String street, String zip){
		
		HashMap<String, ArrayList<String>> controllers = this.getControllerTable();
		HashMap<String, ArrayList<String>> coffeeMakers = this.getCoffeeMakerTable();
		
		ArrayList<String> machine;
		String controllerID;
		MachineType currentType;
		ArrayList<String> controller;
		
		for (String machineID : coffeeMakers.keySet()){
			
			machine = coffeeMakers.get(machineID);
			
			for (int i = 1; i < machine.size(); i++){
				
				controllerID = machine.get(i);
				controller = controllers.get(controllerID);
				currentType = MachineType.stringToMachineType(machine.get(0));
				
				if (currentType == machineType && controller.get(0).equals(street) && controller.get(1).equals(zip)){
					
					String[] machineIdentification = new String[] {machineID, controllerID};
					return machineIdentification;
				}
			}
		}
		
		System.out.println("ERROR: Could not find machine with specified parameters.");
		return null;
	}
	
	private HashMap<String, ArrayList<String>> getCoffeeMakerTable(){
		
		return this.readTable(this.coffeeMakerFile);
	}
	
	private HashMap<String, ArrayList<String>> getCondimentTable(){
		
		return this.readTable(this.condimentFile);
	}
	
	private HashMap<String, ArrayList<String>> getControllerTable(){
		
		return this.readTable(this.controllerFile);
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
}
