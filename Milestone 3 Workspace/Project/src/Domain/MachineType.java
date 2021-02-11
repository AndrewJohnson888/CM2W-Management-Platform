package Domain;

/**
 * 
 * @author johnsoa8
 * Description: Simple class used to easily reference the different machine types, and to convert between their string formss
 *
 */
public enum MachineType {

	SIMPLE,
	AUTOMATED,
	PROGRAMMABLE;
	
	public static MachineType stringToMachineType(String machineType){
			
		switch(machineType){
		
			case "Simple":
				return MachineType.SIMPLE;
			case "Automated":
				return MachineType.AUTOMATED;
			case "Programmable":
				return MachineType.PROGRAMMABLE;
		}
		
		return null;
	}
}
