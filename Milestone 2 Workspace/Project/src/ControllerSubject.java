
public interface ControllerSubject {
	
	public void registerControllerObserver(ControllerObserver c);
	public void removeControllerObserver(ControllerObserver c);
	public String notifyControllerObserver(Order o);
	public String drinkResponse(String jresponse);
}
