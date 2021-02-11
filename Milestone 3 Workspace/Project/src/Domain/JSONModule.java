package Domain;

import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author johnsoa8
 * Description: This class knows how to interact with the json strings. This includes creating json strings as well as extracting information
 * 		from them. It uses an external library called JSON.simple in order to do so. A jar file (json-simple-1.1.1.jar) is needed
 * 		to use this library. Make sure this jar file is included in the build path and the build path has the correct location to this 
 * 		jar file.
 *
 */
public class JSONModule {
	
	private static final String ORDER_KEY = "order";
	private static final String CONDIMENTS_KEY = "condiments"; 
	private static final String NAME_KEY = "name"; 
	private static final String QUANTITY_KEY = "qty"; 
	private static final String ORDER_ID_KEY = "orderID"; 
	private static final String ADDRESS_KEY = "address"; 
	private static final String STREET_KEY = "street"; 
	private static final String ZIP_KEY = "ZIP"; 
	private static final String DRINK_RESPONSE_KEY = "drinkresponse"; 
	private static final String DRINK_KEY = "drink"; 
	private static final String STATUS_KEY = "status"; 
	private static final String ERROR_DESCRIPTION_KEY = "errordesc"; 
	private static final String ERROR_CODE_KEY = "errorcode"; 
	
	private JSONParser jparser;
	private JSONObject jobject;
	private JSONArray jarray;
	private HashMap<String, String> fields;
	private HashMap<String, Integer> condiments;
	
	public JSONModule(){
		
		this.jparser = new JSONParser();
		this.jobject = new JSONObject();
		this.jarray = new JSONArray();
		this.fields = new HashMap<String, String>();
		this.condiments = new HashMap<String, Integer>();
	}
	
	public void parseResponse(String response){
		
		JSONObject jresponse = this.parseString(response);
		jresponse = this.getJSON(jresponse, DRINK_RESPONSE_KEY);
		
		this.fields.put(ORDER_ID_KEY, this.getLongToString(jresponse, ORDER_ID_KEY));
		this.fields.put(STATUS_KEY, this.getLongToString(jresponse, STATUS_KEY));
		
		if (this.getStatus() != 0){
		
			this.fields.put(ERROR_DESCRIPTION_KEY, this.getString(jresponse, ERROR_DESCRIPTION_KEY));
			this.fields.put(ERROR_CODE_KEY, this.getLongToString(jresponse, ERROR_CODE_KEY));
		}
	}
	
	public void parseOrder(String order){
		
		JSONObject jorder = this.parseString(order);
		jorder = this.getJSON(jorder, ORDER_KEY);
		
		JSONArray jcondiments = this.getArray(jorder, CONDIMENTS_KEY);
		
		if (jcondiments != null){
			
			JSONObject item;
			String name;
			int qty;
			
			for (int i = 0; i < jcondiments.size(); i++){
				
				item = (JSONObject) jcondiments.get(i);
				name = this.getString(item, NAME_KEY);
				qty = this.getLongToInteger(item, QUANTITY_KEY);
				this.condiments.put(name, qty); 
			}
		}
		
		this.fields.put(ORDER_ID_KEY, this.getLongToString(jorder, ORDER_ID_KEY));			
		this.fields.put(DRINK_KEY, this.getString(jorder, DRINK_KEY));
		
		JSONObject jaddress = (JSONObject) jorder.get(ADDRESS_KEY);
		this.fields.put(STREET_KEY, this.getString(jaddress, STREET_KEY));
		this.fields.put(ZIP_KEY, this.getLongToString(jaddress, ZIP_KEY));
	}
	
	private JSONObject parseString(String s){
		
		try { return (JSONObject) this.jparser.parse(s); } 
		catch (ParseException e) { System.out.println("ERROR: Could not find field in JSON string: " + s); }
		return null;
	}
	
	private JSONObject getJSON(JSONObject jobject, String s){ return (JSONObject) jobject.get(s); }
	private JSONArray getArray(JSONObject jobject, String s){ return (JSONArray) jobject.get(s); }
	private String getString(JSONObject jobject, String s){ return (String) jobject.get(s); }
	private int getLongToInteger(JSONObject jobject, String s){ return (int) ((long) jobject.get(s)); }
	private String getLongToString(JSONObject jobject, String s){ return Long.toString((long) jobject.get(s)); }
	
	public HashMap<String, Integer> getCondiments() { return this.condiments; };
	public String getDrink() { return this.fields.get(DRINK_KEY); };
	public String getStreet() { return this.fields.get(STREET_KEY); };
	public String getZip() { return this.fields.get(ZIP_KEY); };
	public int getOrderID() { return Integer.parseInt(this.fields.get(ORDER_ID_KEY)); };
	
	public int getStatus() { return Integer.parseInt(this.fields.get(STATUS_KEY)); };
	public String getErrorDescription() { return this.fields.get(ERROR_DESCRIPTION_KEY); };
	public int getErrorCode() { return Integer.parseInt(this.fields.get(ERROR_CODE_KEY)); };
	
	public void put(Object key, Object value){ this.jobject.put(key, value); }
	public void add(Object item){ this.jarray.add(item); };
	
	public String toString(){ 
		
		if (this.jobject.size() != 0) return this.jobject.toJSONString();
		if (this.jarray.size() != 0) return this.jarray.toJSONString();
		return null;
	}
	
	public Object toObject(){ 
		
		if (this.jobject.size() != 0) return this.jobject;
		if (this.jarray.size() != 0) return this.jarray;
		return null;
	}
}