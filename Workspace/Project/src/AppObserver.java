
public interface AppObserver {

	public void completeOrder(int ID);
	public void registerApp(int appID, String[] drinks, String[] locations, String[] condiments);
}
