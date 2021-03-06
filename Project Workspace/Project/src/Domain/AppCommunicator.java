package Domain;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import Presentation.AppObserver;
import Presentation.AppSubject;

public class AppCommunicator implements AppObserver, AppSubject{

	private SubsystemController sc;
	
	public AppCommunicator(SubsystemController sc){
		
		this.sc = sc;
	}
	
	@Override
	public void registerAppObserver(AppObserver a) {
		
		this.sc.addApp(a);
	}

	@Override
	public void removeAppObserver(AppObserver a) {
		
		this.sc.removeApp(a);
	}
	
	public String addOrder(String jorder){
		
		try {
			
			Order order = Order.parseOrder(jorder);
			this.sc.getOrders().add(order);
			return this.sc.sendCommand(order);
		}
		
		catch (ParseException e) {

			System.out.println("ERROR: Invalid Order Format.");
		}
		
		return null;
	}

	@Override
	//would normally send data to identified app, but will print out instead for testing
	public String notifyAppObserver(Order o) {
		
		String jresponse = o.constructResponse();
		return jresponse;
	}
}
