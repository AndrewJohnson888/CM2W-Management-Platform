
import java.util.ArrayList;
import java.util.HashMap;

public class ControllerLocator {
	
	private PersistenceInterface pi;

	public ControllerLocator(){
		
		this.pi = new PersistenceInterface();
	}
	
	public int getControllerID(String street, String zip, String capability){
		
		HashMap<String, ArrayList<String>> controllerTable = this.pi.getControllerTable();
		
		for (String id : controllerTable.keySet()){
			
			if (controllerTable.get(id).get(1).equals(street) && controllerTable.get(id).get(2).equals(zip)){
				
				if (controllerTable.get(id).get(0).equals(capability)){
				
					return Integer.parseInt(id);
				}
			}
		}
		
		System.out.println("ERROR: Could not find controller with desired capability at provided location.");
		return -1;
	}
	
	public int getCoffeeMachineID(int controllerID, String capability){
		
		String controllerIDString = Integer.toString(controllerID);
		
		HashMap<String, ArrayList<String>> coffeeMakerTable = this.pi.getCoffeeMakerTable();
		HashMap<String, ArrayList<String>> coffeeMakerCapabilityTable = this.pi.getCoffeeMakerCapabilityTable();
		
		for (String machineID : coffeeMakerTable.keySet()){
			
			if (coffeeMakerTable.get(machineID).get(0).equals(controllerIDString)){
				
				for (String coffeeMakerID : coffeeMakerCapabilityTable.keySet()){
				
					if (machineID.equals(coffeeMakerID) && coffeeMakerCapabilityTable.get(coffeeMakerID).contains(capability)){
						
						return Integer.parseInt(coffeeMakerID);
					}
				}
			}
		}
		
		System.out.println("ERROR: Could not find coffee maker at controller to fulfill order.");
		return -1;
	}
}
