package Domain;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Presentation.AppCommunicator;
import Presentation.ControllerCommunicator;

/**
 * 
 * @author johnsoa8
 * Description: This is the central hub class which controls the flow of the system and the code "glue" which tethers the rest of the 
 * 		system together. It determines which orders to fulfill, which responses to send, and knows how. 
 * 		It has a timer which checks for new orders and responses every so often. If there is a new thing, it deals with it by 
 * 		delegating tasks to other classes and dealing only with high level stuff.
 *
 */
public class SystemControl {
	
	private static final int FULFILL_ORDER_TIME = 1000; // milliseconds

	private Timer timer;
	private AppCommunicator appComm;
	private ControllerCommunicator controllerComm;
	private OrderFactory factory;
	private ArrayList<Order> orders;
	
	public SystemControl(){
		
		this.orders = new ArrayList<Order>();
		
		this.appComm = AppCommunicator.getInstance();
		this.controllerComm = ControllerCommunicator.getInstance();
		this.factory = new ConcreteOrderFactory();
		
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {
			
			@Override
			public void run(){
				
				handleOrder();
				handleResponse();
			}
		}, 0, FULFILL_ORDER_TIME);
	}
	
	public void shutdown(){ this.timer.cancel(); }
	
	private void handleOrder(){

		String order = this.appComm.pollOrder();
		int appID = this.appComm.pollAppID();
		
		if (order == null) return;
			
		Order o = this.factory.createOrder(order, appID);
		this.orders.add(o);
		
		String command = o.createCommand();
		this.controllerComm.notifyControllerObserver(command, o.getControllerID());
	}
	
	private void handleResponse(){
		
		String response = this.controllerComm.pollResponse();
		
		if (response == null) return;
				
		JSONModule jmodule = new JSONModule();
		jmodule.parseResponse(response);
		
		Order order = null;
		for (Order o : this.orders) if (o.checkID(jmodule.getOrderID())) order = o;
		
		order.parseResponse(response);
		String appResponse = order.createResponse();
		this.appComm.notifyAppObserver(appResponse, order.getAppID());
	}
}