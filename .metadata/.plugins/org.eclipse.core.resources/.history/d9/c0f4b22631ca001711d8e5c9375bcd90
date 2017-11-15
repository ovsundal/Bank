package test;

import java.util.Properties;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.*;
import org.junit.Test;
import sessionsBeans.KundeImportRemote;
import entity.*;
import junit.framework.TestCase;

public class KundeImportTest extends TestCase {

	Context context;

	@EJB
	// Enheten vi opererer på
	KundeImportRemote kir;

	public void setUp() throws Exception {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
		p.put("BankDBDatasource", "new://Resource?type=DataSource");
		p.put("BankDBDatasource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("BankDBDatasource.JdbcUrl", "jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource", "new://Resource?type=DataSource");
		p.put("Unmanaged_BankDBDataSource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("Unmanaged_BankDBDataSource.JdbcUrl", "jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource.JtaManaged", "false");
		context = new InitialContext(p);
		kir = (KundeImportRemote) context.lookup("KundeImportRemote");
	}

	@Override
	public void tearDown() throws Exception {}

	@Test
	public final void testNewKunde() {
		try {
			Calendar c = Calendar.getInstance();
			c.set(1968, 00, 00, 11, 11, 11);
			Person p = new Person((Date) c.getTime(), "53421", "SnurpenotJohansen", "Lavpannebakke2", "", "8515",
					"Narvik", "FlåklypaGrandPrix", "1231", "Tidligere stunt reporter");
			kir.newKunde(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}