package Presentation;

import Domain.Order;

public interface AppObserver {

	public String notifyAppObserver(Order o);
}
