package Domain;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import DataSource.Order;
import Presentation.Main;

public abstract class Controller implements ControllerObserver{

	private static final int QUEUE_SIZE = 10;
	private static final int MAKE_COFFEE_TIME = 5; //in seconds
	private int id;
	private Queue<Order> orderQueue;
	private Timer timer; //simulates length of time it takes to make coffee or process an order
	private ControllerSubject subject;
	private boolean available; //simulates whether or not this controller is available
	
	protected MachineCapabilityBehavior behavior;
	
	public Controller(ControllerSubject subject, int controllerID, boolean available){
		
		this.id = controllerID;
		
		this.available = available;
		
		this.orderQueue = new ArrayBlockingQueue<Order>(QUEUE_SIZE);
		
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {
			
			@Override
			public void run(){
				
				if (Controller.this.orderQueue.isEmpty()) return;
				
				Order o = Controller.this.orderQueue.poll();
				
				boolean responded = Controller.this.behavior.makeCoffee(o);
				
				if (! responded){
					
					Main.simulateDelay();
					System.out.println("The Controller is not responding");
					
					o.setStatusMessage("Controller is not responding");
					Controller.this.subject.updateOrder(o.getAppID(), o.getOrderIDInteger());
					return;
				}

				Controller.this.subject.updateOrder(o.getAppID(), o.getOrderIDInteger());
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
		
		return this.available && ! (this.orderQueue.size() == QUEUE_SIZE);
	}

	@Override
	public void addOrder(Order o) {
		
		this.orderQueue.add(o);
	}
}
