package Domain;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

import DataSource.Instruction;

public abstract class Controller implements ControllerObserver{

	private static final int QUEUE_SIZE = 10;
	private static final int MAKE_COFFEE_TIME = 1; //in seconds
	private int id;
	private Queue<Instruction> instructionQueue;
	private Timer timer;
	private ControllerSubject subject;
	private boolean available;
	
	protected MachineCapabilityBehavior behavior;
	
	public Controller(ControllerSubject subject, int controllerID, boolean available){
		
		this.id = controllerID;
		
		this.available = available;
		
		this.instructionQueue = new ArrayBlockingQueue<Instruction>(QUEUE_SIZE);
		
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {
			
			@Override
			public void run(){
				
				if (Controller.this.instructionQueue.isEmpty()) return;
				
				Instruction i = Controller.this.instructionQueue.poll();
				
				boolean responded = Controller.this.behavior.makeCoffee(i);
				
				if (! responded){
					
					Controller.this.subject.completeOrder(i.getAppID(), i.getOrderID(), "Controller is not responding");
					return;
				}
				
				System.out.println("coffee order has been made");

				Controller.this.subject.completeOrder(i.getAppID(), i.getOrderID(), "");
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
		
		return this.available && ! (this.instructionQueue.size() == QUEUE_SIZE);
	}

	@Override
	public void addInstruction(Instruction i) {
		
		this.instructionQueue.add(i);
	}
}
