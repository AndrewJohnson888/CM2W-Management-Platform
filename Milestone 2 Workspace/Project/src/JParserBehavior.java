import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class JParserBehavior extends JSONParser {
	
	protected ControllerLocator locator;
	
	public JParserBehavior(){
		
		this.locator = new ControllerLocator();
	}
	
	public Response jparseResponse(String jresponse) throws ParseException{
		
		JSONObject jobject = (JSONObject) this.parse(jresponse);
		jobject = this.getObject(jobject, "drinkresponse");
		
		int orderID = this.getInteger(jobject, "orderID");
		int status = this.getInteger(jobject, "status");
		String message = this.getString(jobject, "errordesc");
		
		Response response = new Response(orderID, status, message);
		
		return response;
	}
	
	public abstract Order jparse(String jstring) throws ParseException;
	
	protected JSONObject getObject(JSONObject jobject, String key) throws ParseException{
		
		if (jobject.containsKey(key)) return (JSONObject) jobject.get(key);
		return null;
	}
	
	protected JSONArray getArray(JSONObject jobject, String key) throws ParseException{
		
		if (jobject.containsKey(key)) return (JSONArray) jobject.get(key);
		return null;
	}
	
	protected JSONObject getArrayObject(JSONArray jarray, int index) throws ParseException{
		
		if (jarray.size() > index) return (JSONObject) jarray.get(index);
		return null;
	}
	
	protected String getString(JSONObject jobject, String key) throws ParseException{
		
		if (jobject.containsKey(key)) return (String) jobject.get(key);
		return null;
	}
	
	protected int getInteger(JSONObject jobject, String key) throws ParseException{
		
		if (jobject.containsKey(key)) {
			
			long longValue = (long) jobject.get(key);
			return (int) longValue;
		}
		return -1;
	}
}
