package Domain;
import DataSource.Order;
import Presentation.AppObserver;

public interface AppSubject {

	public boolean registerAppObserver(AppObserver a);
	public boolean removeAppObserver(AppObserver a);
	public boolean updateOrder(int appID, int orderID);
	public void addOrder(Order o);
}
