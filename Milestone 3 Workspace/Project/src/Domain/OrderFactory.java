package Domain;

/**
 * 
 * @author johnsoa8
 * Description: Interface for an OrderFactory, which creates an order from its json string
 *
 */
public abstract class OrderFactory {

	public abstract Order createOrder(String order, int appID);
}
