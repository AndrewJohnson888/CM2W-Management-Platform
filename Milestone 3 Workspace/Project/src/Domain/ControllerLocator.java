package Domain;

import java.util.ArrayList;

import DataSource.PersistenceInterface;

/**
 * 
 * @author johnsoa8
 * Description: This class finds a machine to execute a coffee order given the address and some information about the type of controller.
 * 		It uses the interface to the database (or whatever storage medium) to aid in finding a correct machine. 
 *
 */
public class ControllerLocator {
	
	private PersistenceInterface pi;
	
	private ArrayList<String> lastRecipe;
	private int lastMachineID;
	private int lastControllerID;
	private MachineType machineType;

	public ControllerLocator(){
		
		this.pi = new PersistenceInterface();
		
		this.lastRecipe = null;
		this.lastMachineID = -1;
		this.lastControllerID = -1;
		this.machineType = null;
	}
	
	public void locateMachine(boolean hasCondiments, String drink, String street, String zip){
		
		this.lastRecipe = this.pi.getRecipe(drink);
		
		this.machineType = MachineType.SIMPLE;
		if (hasCondiments) this.machineType = MachineType.AUTOMATED;
		if (this.lastRecipe != null) this.machineType = MachineType.PROGRAMMABLE;
		
		String[] machineIdentification = this.pi.findMachine(this.machineType, street, zip);
		this.lastMachineID = Integer.parseInt(machineIdentification[0]);
		this.lastControllerID = Integer.parseInt(machineIdentification[1]);
	}
	
	public int getMachine(){ return this.lastMachineID; } 
	public int getController(){ return this.lastControllerID; } 
	public ArrayList<String> getRecipe(){ return this.lastRecipe; } 
	public MachineType getType(){ return this.machineType; }
}
