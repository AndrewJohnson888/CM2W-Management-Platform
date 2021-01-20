import java.util.ArrayList;

public class Instruction {
	
	private int appID;
	private int orderID;
	private ArrayList<String> recipe;
	private ArrayList<String> condiments;

	public Instruction(int appID, int orderID, ArrayList<String> condiments, ArrayList<String> recipe){
		
		this.appID = appID;
		this.orderID = orderID;
		this.condiments = condiments;
		this.recipe = recipe;
	}
	
	public int getAppID(){
		
		return this.appID;
	}
	
	public int getOrderID(){
		
		return this.orderID;
	}
	
	public String getCondimentAtIndex(int index){
		
		return this.condiments.get(index);
	}
	
	public int getNumberOfCondiments(){
		
		return this.condiments.size();
	}
	
	public String getRecipeAtIndex(int index){
		
		return this.recipe.get(index);
	}
	
	public int getNumberOfRecipeSteps(){
		
		return this.recipe.size();
	}
}
