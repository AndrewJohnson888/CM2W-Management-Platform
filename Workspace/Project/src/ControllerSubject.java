
public interface ControllerSubject {

	public boolean registerControllerObserver(ControllerObserver a);
	public boolean completeOrder(int appID, int orderID);
}
