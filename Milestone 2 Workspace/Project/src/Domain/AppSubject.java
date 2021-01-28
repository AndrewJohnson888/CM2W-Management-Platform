package Domain;

import Presentation.AppObserver;

public interface AppSubject {

	public void registerAppObserver(AppObserver a);
	public void removeAppObserver(AppObserver a);
	public String notifyAppObserver(Order o);
	public String addOrder(String jorder);
}
