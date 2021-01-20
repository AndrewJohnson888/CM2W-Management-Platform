import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class Controller implements ControllerObserver{

	private static final int QUEUE_SIZE = 10;
	private static final int MAKE_COFFEE_TIME = 5; //in seconds
	private int id;
	private Queue<Instruction> instructionQueue;
	private Timer timer;
	private ControllerSubject subject;
	
	protected MachineCapabilityBehavior behavior;
	
	public Controller(ControllerSubject subject, int controllerID){
		
		this.id = controllerID;
		
		this.instructionQueue = new ArrayBlockingQueue<Instruction>(QUEUE_SIZE);
		
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {
			
			@Override
			public void run(){
				
				if (Controller.this.instructionQueue.isEmpty()) return;
				
				Instruction i = Controller.this.instructionQueue.poll();
				
				Controller.this.behavior.makeCoffee(i);

				Controller.this.subject.completeOrder(i.getAppID(), i.getOrderID());
			}
		}, 0, MAKE_COFFEE_TIME * 1000);
		
		this.subject = subject;
		this.subject.registerControllerObserver(this);
	}

	@Override
	public int registerID() {

		return this.id;
	}

	@Override
	public boolean checkAvailability() {
		
		return ! (this.instructionQueue.size() == QUEUE_SIZE);
	}

	@Override
	public void addInstruction(Instruction i) {
		
		this.instructionQueue.add(i);
	}
}
