package Presentation;

import Domain.Order;

public interface ControllerSubject {
	
	public void registerControllerObserver(ControllerObserver c);
	public void removeControllerObserver(ControllerObserver c);
	public String drinkResponse(String jresponse);
}
