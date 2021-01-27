import org.json.simple.parser.ParseException;

public class JSONModule {
	
	private JParserBehavior jparser;
	private JParserFactory jfactory;

	public JSONModule(){
		
		this.jfactory = new JParserFactory();
	} 
	
	public Order parseOrder(String jorder) throws ParseException {
			
		 this.jparser = this.jfactory.determineBehavior(jorder);
		 Order order = this.jparser.jparse(jorder);
		 
		 return order;
	}
}
