import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

public class SimpleController extends Controller{

	private static final int QUEUE_SIZE = 10;
	private static int NEXT_ID = 0;
	private int id;
	private Queue<Instruction> instructionQueue;
	private Timer timer;
	private ControllerSubject subject;
	
	public SimpleController(ControllerSubject subject){
		
		this.subject = subject;
		this.subject.registerControllerObserver(this);
		
		this.id = SimpleController.NEXT_ID;
		SimpleController.NEXT_ID ++;
		
		this.instructionQueue = new ArrayBlockingQueue<Instruction>(QUEUE_SIZE);
		
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {
			
			@Override
			public void run(){
				
				executeOrder();
			}
		}, 0, 1 * 1000);
	}
	
	private void executeOrder(){
		
		if (this.instructionQueue.isEmpty()) return;
		
		Instruction i = this.instructionQueue.poll();

		
		this.subject.completeOrder(i.getAppID(), i.getOrderID());
	}

	@Override
	public boolean checkID(int ID) {

		return this.id == ID;
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
