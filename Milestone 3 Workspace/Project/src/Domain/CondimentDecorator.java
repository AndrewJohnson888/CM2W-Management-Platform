package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This is the decorator class in the decorator pattern. Condiments may decorate other coffee classes.
 *
 */
public class CondimentDecorator extends Coffee {

	private Coffee wrapped;
	private String name;
	
	public CondimentDecorator(String name, Coffee wrapped){
		
		this.wrapped = wrapped;
		this.name = name;
	}

	@Override
	public HashMap<String, Integer> getCondiments() {
		
		HashMap<String, Integer> condiments = this.wrapped.getCondiments();
		if (condiments.containsKey(this.name)) condiments.put(this.name, condiments.get(this.name) + 1);
		else condiments.put(this.name, 1);
		
		return condiments;
	} 
	
	public ArrayList<String> getRecipe() { return this.wrapped.getRecipe(); } 
	public String getName() { return this.wrapped.getName(); }
}
