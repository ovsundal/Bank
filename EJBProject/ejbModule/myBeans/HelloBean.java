package myBeans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/*** Session Bean implementation class HelloBean */
@Stateless
@LocalBean
public class HelloBean implements HelloBeanRemote {
	private String visitors;

	/*** Default constructor. */
	public HelloBean() {
		visitors = new String();
	}

	public String sayHello(String to) {
		String retval = "Hei og hå til \"" + to + "\"";
		if (visitors.equals("")) {
			visitors = to;
			return retval;
		}
		retval += ". Samtidig en hilsning til de som var her før, nemlig: " + visitors;
		visitors += ", " + to;
		return retval;
	}
}