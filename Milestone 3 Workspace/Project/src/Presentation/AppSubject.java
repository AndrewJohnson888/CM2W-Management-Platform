package Presentation;

/**
 * 
 * @author johnsoa8
 * Description: Implements the Subject part of the observer pattern for apps
 *
 */
public interface AppSubject {

	public void addOrder(String order, int appID);
	public void notifyAppObserver(String response, int appID);
	public int registerAppObserver(AppObserver app);
	public void removeAppObserver(int appID);
}
