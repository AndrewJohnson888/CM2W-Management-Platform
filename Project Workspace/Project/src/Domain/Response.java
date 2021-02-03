package Domain;

public class Response {
	
	private int orderID;
	private int status;
	private String message;

	public Response(int orderID, int status, String message){
		
		this.orderID = orderID;
		this.status = status;
		this.message = message;
	}
	
	public int getOrderID(){
		
		return this.orderID;
	}
	
	public int getStatus(){
		
		return this.status;
	}
	
	public String getMessage(){
		
		return this.message;
	}
}
