package Presentation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author johnsoa8
 * Description: A class used for communication with apps and storing their requests until the system control has time to deal with them.
 * 		This is where the queue of app requests is stored.
 *
 */
public class AppCommunicator implements AppSubject{
	
	private static AppCommunicator uniqueInstance = new AppCommunicator();
	private static int NEXT_APP_ID = 1;
	
	private Queue<String> orderQueue;
	private Queue<Integer> appIDQueue;
	private HashMap<Integer, AppObserver> apps;

	public AppCommunicator(){ 
		
		this.orderQueue = new LinkedList<String>();
		this.appIDQueue = new LinkedList<Integer>();
		this.apps = new HashMap<Integer, AppObserver>();
	}
	
	public static AppCommunicator getInstance(){ return AppCommunicator.uniqueInstance; }
	
	public int registerAppObserver(AppObserver app) {
		
		this.apps.put(NEXT_APP_ID, app);
		NEXT_APP_ID ++;
		
		return NEXT_APP_ID - 1;
	}

	public void removeAppObserver(int appID) {
		
		this.apps.remove(appID);
	}
	
	public void addOrder(String order, int appID){ 
		
		this.orderQueue.add(order);
		this.appIDQueue.add(appID);
	}

	public int pollAppID(){ 
		
		if (this.appIDQueue.isEmpty()) return -1; 
		return this.appIDQueue.poll(); 
	}
	
	public String pollOrder(){ return this.orderQueue.poll(); }
	public void notifyAppObserver(String response, int appID){ this.apps.get(appID).updateApp(response); }
}