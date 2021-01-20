package Presentation;

public interface AppObserver {

	public void completeOrder(int ID, String errorMessage);
	public void registerApp(int appID, String[] drinks, String[] locations, String[] condiments);
}
