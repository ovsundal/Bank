package clients;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import myBeans.*;

public class SimpleClient {
	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, 
				"org.apache.openejb.client.RemoteInitialContextFactory");
		Context context = new InitialContext(props);
		HelloBeanRemote hello = (HelloBeanRemote) context.lookup("HelloBeanRemote");
		System.out.println(hello.sayHello("Snorken"));
		System.out.println(hello.sayHello("Mummitrollet"));
		System.out.println(hello.sayHello("Lille My"));
	}
}