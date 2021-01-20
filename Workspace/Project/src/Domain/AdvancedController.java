package Domain;

public class AdvancedController extends Controller{

	public AdvancedController(ControllerSubject subject, int controllerID, boolean available, boolean isResponding) {
		
		super(subject, controllerID, available);
		
		this.behavior = new ProduceCoffeeAndCondimentsBehavior(isResponding);
	}
}
