
public class AdvancedController extends Controller{

	public AdvancedController(ControllerSubject subject, int controllerID) {
		
		super(subject, controllerID);
		
		this.behavior = new ProduceCoffeeAndCondimentsBehavior();
	}
}
