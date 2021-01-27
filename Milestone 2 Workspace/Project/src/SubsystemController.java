import org.json.simple.parser.ParseException;

public class SubsystemController {
	
	private JSONModule jmodule;

	public SubsystemController(){
		
		this.jmodule = new JSONModule();
	}
	
	public void addOrder(String jorder){
		
		try {
			
			Order order = this.jmodule.parseOrder(jorder);
		}
		
		catch (ParseException e) {

			System.out.println("ERROR: Invalid Order Format.");
		}
	}
}
