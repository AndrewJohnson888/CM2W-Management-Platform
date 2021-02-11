package Testing;

import static org.junit.Assert.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author johnsoa8
 * Description: A few helpful testing methods which compare JSON strings.
 * 		The JSON strings outputted by the JSON.simple library functions (see JSONModule class) are not in any guaranteed order.
 * 		Therefore, we need these methods to ensure that the system's outputted json files (which may be in a jumbled order
 *      compared to what was passed in) are equivalent to the expected result. 
 *
 */
public class TestingUtilities {
	
	public static void compareCommandJSON(String output, String expected, String type) throws ParseException{
		
		JSONParser jparser = new JSONParser();
		
		JSONObject jobject1 = (JSONObject) jparser.parse(output);
		JSONObject jobject2 = (JSONObject) jparser.parse(expected);
		
		assertEquals(true, jobject1.containsKey("command"));
		assertEquals(true, jobject2.containsKey("command"));
		
		jobject1 = (JSONObject) jobject1.get("command");
		jobject2 = (JSONObject) jobject2.get("command");
		
		assertEquals(jobject2.get("controller_id"), jobject1.get("controller_id"));
		assertEquals(jobject2.get("coffee_machine_id"), jobject1.get("coffee_machine_id"));
		assertEquals(jobject2.get("orderID"), jobject1.get("orderID"));
		assertEquals(jobject2.get("DrinkName"), jobject1.get("DrinkName"));
		assertEquals(jobject2.get("Requesttype"), jobject1.get("Requesttype"));
		
		if (type.equals("Automated") || type.equals("Programmable")){
			
			JSONArray jarray1 = (JSONArray) jobject1.get("Options:");
			JSONArray jarray2 = (JSONArray) jobject2.get("Options:");
		
			for (int i = 0; i < jarray1.size(); i++){
	
				JSONObject jitem1 = (JSONObject) jarray1.get(i);
				JSONObject jitem2 = (JSONObject) jarray2.get(i);
				
				assertEquals(jitem2.get("Name"), jitem1.get("Name"));
				assertEquals(jitem2.get("qty"), jitem1.get("qty"));
			}
		}
		
		if (type.equals("Programmable")){
			
			JSONArray jarray1 = (JSONArray) jobject1.get("Recipe");
			JSONArray jarray2 = (JSONArray) jobject2.get("Recipe");
			
			for (int i = 0; i < jarray1.size(); i++){
				
				JSONObject jitem1 = (JSONObject) jarray1.get(i);
				JSONObject jitem2 = (JSONObject) jarray2.get(i);
				
				assertEquals(jitem2.get("commandstep"), jitem1.get("commandstep"));
				assertEquals(jitem2.get("object"), jitem1.get("object"));
			}
		}
	}
	
	public static void compareResponseJSON(String output, String expected) throws ParseException {
		
		JSONParser jparser = new JSONParser();
		
		JSONObject jobject1 = (JSONObject) jparser.parse(output);
		JSONObject jobject2 = (JSONObject) jparser.parse(expected);
		
		assertEquals(true, jobject1.containsKey("user-response"));
		assertEquals(true, jobject2.containsKey("user-response"));
		
		jobject1 = (JSONObject) jobject1.get("user-response");
		jobject2 = (JSONObject) jobject2.get("user-response");
		
		assertEquals(jobject2.get("orderID"), jobject1.get("orderID"));
		assertEquals(jobject2.get("coffee_machine_id"), jobject1.get("coffee_machine_id"));
		assertEquals(jobject2.get("status"), jobject1.get("status"));
		assertEquals(jobject2.get("status-message"), jobject1.get("status-message"));
		assertEquals(jobject2.get("error-message"), jobject1.get("error-message"));
	}
}
