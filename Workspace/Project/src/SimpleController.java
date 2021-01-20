
public class SimpleController extends Controller{
	
	public SimpleController(ControllerSubject subject, int controllerID){
		
		super(subject, controllerID);
		
		this.behavior = new ProduceCoffeeOnlyBehavior();
	}
}
