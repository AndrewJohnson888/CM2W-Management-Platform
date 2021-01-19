
public interface AppSubject {

	public boolean registerAppObserver(AppObserver a);
	public boolean removeAppObserver(AppObserver a);
	public boolean notifyAppObserver(int appID);
	public boolean addOrder(Order o);
}
