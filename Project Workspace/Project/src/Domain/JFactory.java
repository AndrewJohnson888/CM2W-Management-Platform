package Domain;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JFactory {
	
	private JSONParser jparser;
	private JParserBehavior simpleJParser;
	private JParserBehavior automatedJParser;

	public JFactory(){
		
		this.jparser = new JSONParser();
		this.simpleJParser = new SimpleJParser();
		this.automatedJParser = new AutomatedJParser();
	}
	
	public JParserBehavior initParser(){
		
		return this.simpleJParser;
	}
	
	public JParserBehavior determineParsingBehavior(String jstring) throws ParseException{
		
		JSONObject jobject = (JSONObject) this.jparser.parse(jstring);
		JSONObject jorder = (JSONObject) jobject.get("order");
		
		if (jorder.containsKey("condiments")) return this.automatedJParser;
		return this.simpleJParser;
	}
}
