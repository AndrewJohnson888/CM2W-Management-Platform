package Presentation;

import Domain.Order;

public interface AppSubject {

	public void registerAppObserver(AppObserver a);
	public void removeAppObserver(AppObserver a);
	public String addOrder(String jorder);
}
