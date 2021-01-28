package Domain;
import org.json.simple.parser.ParseException;

public class JSONModule {
	
	private JParserBehavior jparser;
	private JFactory jfactory;

	public JSONModule(){
		
		this.jfactory = new JFactory();
		this.jparser = this.jfactory.initParser();
	} 
	
	public Order parseOrder(String jorder) throws ParseException {
			
		 this.jparser = this.jfactory.determineParsingBehavior(jorder);
		 Order order = this.jparser.jparse(jorder);
		 return order;
	}
	
	public Response parseResponse(String jresponse) throws ParseException{
		
		Response response = this.jparser.jparseResponse(jresponse);
		return response;
	}
}
