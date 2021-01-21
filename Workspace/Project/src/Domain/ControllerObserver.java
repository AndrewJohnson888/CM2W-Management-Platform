package Domain;

import DataSource.Order;

public interface ControllerObserver {

	public int registerID();
	public boolean checkAvailability();
	public void addOrder(Order o);
}
