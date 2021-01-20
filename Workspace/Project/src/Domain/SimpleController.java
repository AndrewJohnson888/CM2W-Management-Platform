package Domain;

public class SimpleController extends Controller{
	
	public SimpleController(ControllerSubject subject, int controllerID, boolean available, boolean isResponding){
		
		super(subject, controllerID, available);
		
		this.behavior = new ProduceCoffeeOnlyBehavior(isResponding);
	}
}
