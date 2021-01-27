import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class JParserBehavior extends JSONParser {
	
	// make custom exception if json doesn't contain key
	
	public abstract Order jparse(String jstring) throws ParseException;
	
	protected JSONObject getObject(JSONObject jobject, String key){
		
		if (jobject.containsKey(key)) return (JSONObject) jobject.get(key);
		return null;
	}
	
	protected JSONArray getArray(JSONObject jobject, String key){
		
		if (jobject.containsKey(key)) return (JSONArray) jobject.get(key);
		return null;
	}
	
	protected String getString(JSONObject jobject, String key){
		
		if (jobject.containsKey(key)) return (String) jobject.get(key);
		return null;
	}
	
	protected int getInteger(JSONObject jobject, String key){
		
		if (jobject.containsKey(key)) return (int) jobject.get(key);
		return -1;
	}
}
