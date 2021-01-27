import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JParserFactory {
	
	private JSONParser jparser;

	public JParserFactory(){
		
		this.jparser = new JSONParser();
	}
	
	public JParserBehavior determineBehavior(String jstring) throws ParseException{
		
		JSONObject jobject = (JSONObject) this.jparser.parse(jstring);
		JSONObject jorder = (JSONObject) jobject.get("order");
		
		if (jorder.containsKey("condiments")) return new AdvancedJParser();
		return new SimpleJParser();
	}
}
