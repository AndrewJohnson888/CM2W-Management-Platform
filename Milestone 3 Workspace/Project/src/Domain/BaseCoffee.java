package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This class is the base class for the decorator pattern. It represents a base coffee, such as Latte, Americano, etc.
 *
 */
public class BaseCoffee extends Coffee{

	private ArrayList<String> recipe;
	private String name;
	
	public BaseCoffee(String name, ArrayList<String> recipe){
		
		this.name = name;
		this.recipe = recipe;
	}

	public HashMap<String, Integer> getCondiments() { return new HashMap<String, Integer>(); }
	public ArrayList<String> getRecipe() { return this.recipe; }
	public String getName() { return this.name; }
}
