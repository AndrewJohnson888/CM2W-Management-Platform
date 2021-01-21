package Domain;

public interface ControllerSubject {

	public boolean removeControllerObserver(ControllerObserver c);
	public boolean registerControllerObserver(ControllerObserver c);
	public boolean updateOrder(int appID, int orderID);
}
