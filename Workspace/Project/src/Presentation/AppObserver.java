package Presentation;

public interface AppObserver {

	public void updateOrder(int orderID);
	public void registerApp(int appID, String[] drinks, String[] locations, String[] condiments);
}
