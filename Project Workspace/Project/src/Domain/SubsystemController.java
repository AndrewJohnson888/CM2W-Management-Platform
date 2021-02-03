package Domain;
import java.util.ArrayList;
import Presentation.AppObserver;
import Presentation.AppSubject;
import Presentation.ControllerObserver;
import Presentation.ControllerSubject;

public class SubsystemController{
	
	private ControllerCommunicator cc;
	private AppCommunicator ac;
	
	private ArrayList<ControllerObserver> controllers;
	private ArrayList<AppObserver> apps;
	private ArrayList<Order> orders;
	
	public SubsystemController(){
		
		this.controllers = new ArrayList<ControllerObserver>();
		this.apps = new ArrayList<AppObserver>();
		this.orders = new ArrayList<Order>();
		
		this.cc = new ControllerCommunicator(this);
		this.ac = new AppCommunicator(this);
	}
	
	public AppSubject getAppCommunicator(){
		
		return this.ac;
	}
	
	public ControllerSubject getControllerCommunicator(){
		
		return this.cc;
	}
	
	public ArrayList<Order> getOrders(){
		
		return this.orders;
	}
	
	public void addController(ControllerObserver co){
		
		this.controllers.add(co);
	}
	
	public void removeController(ControllerObserver co){
		
		this.controllers.remove(co);
	}
	
	public void addApp(AppObserver ao){
		
		this.apps.add(ao);
	}
	
	public void removeApp(AppObserver ao){
		
		this.apps.remove(ao);
	}
	
	public String sendResponse(Order o){
		
		return this.ac.notifyAppObserver(o);
	}
	
	public String sendCommand(Order o){
		
		return this.cc.notifyControllerObserver(o);
	}
}
