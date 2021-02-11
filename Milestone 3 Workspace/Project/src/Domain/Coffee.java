package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author johnsoa8
 * Description: This is the highest class in the decorator pattern. The base class and condimetn decorator classes implement this.
 *
 */
public abstract class Coffee {

	public abstract HashMap<String, Integer> getCondiments();
	public abstract ArrayList<String> getRecipe();
	public abstract String getName();
}
