package Presentation;

/**
 * 
 * @author johnsoa8
 * Description: Implements the Subject part of the observer pattern for controllers
 *
 */
public interface ControllerSubject {

	public void addResponse(String response);
	public void notifyControllerObserver(String command, int controllerID);
	public int registerControllerObserver(ControllerObserver controller);
	public void removeControllerObserver(int controllerID);
}
