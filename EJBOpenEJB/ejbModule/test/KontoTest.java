package test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Konto;
import entity.Person;
import junit.framework.TestCase;
import sessionsBeans.KontoerRemote;

public class KontoTest extends TestCase {
	Context context;
	@EJB
	// Enheten vi opererer p�
	KontoerRemote kontoer;
	
	public void setUp() throws Exception {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
		p.put("BankDBDatasource","new://Resource?type=DataSource");
		p.put("BankDBDatasource.JdbcDriver","org.hsqldb.jdbcDriver");
		p.put("BankDBDatasource.JdbcUrl","jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource","new://Resource?type=DataSource");
		p.put("Unmanaged_BankDBDataSource.JdbcDriver","org.hsqldb.jdbcDriver");
		p.put("Unmanaged_BankDBDataSource.JdbcUrl","jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource.JtaManaged","false");
		context= new InitialContext(p);
		kontoer= (KontoerRemote)context.lookup("KontoerRemote");
	}
	
	@Override
	public void tearDown() throws Exception {}
	
	public void test() throws Exception {
		List<Konto> list = kontoer.list();
		int assertsize = list.size();
		assertEquals( "List.size()", assertsize, list.size());
		addKonto();
		list = kontoer.list();
		assertEquals( "List.size()", assertsize + 1, list.size());
		list();
	}
	
	public void addKonto() throws Exception {
		try{
			Calendar c = Calendar.getInstance();
			c.set(1988, 00, 00, 01, 01, 01);
			
			Person p = new Person((Date) c.getTime(), "34797", "Stisan Sigd", 
					"J�rgen Fisefins terasse 3","", "8515", "Narvik");
			
			Konto k = new Konto();
			System.out.println("Adding account:");
			kontoer.createAccount("test123", "120", new Date(), p);
			System.out.println("Done Adding account:");
		}
		finally{
		}
	}
	
	public void list() throws Exception {
		List<Konto> list = kontoer.list();
		System.out.println(list);
		for(Konto kon : list) {
			System.out.println("Listing : " + kon.getOwner().getNavn() + kon.getDato() + " -" + 
					kon.getNavn() + ": "+ kon.getSaldo());
		}
	}
	
	public void remove() throws Exception {
		try{
			List<Konto> list = kontoer.list();
			for(Konto kon : list) {
				if(kon.getId() > 0) {
					kontoer.remove(kon);
					System.out.println("Deleting ... : "+ kon.getDato() + " -" + 
					kon.getNavn() + ": "+ kon.getSaldo());
					}
				else {
					System.out.println("Delete failed ... : "+ kon.getDato() + " -" + 
							kon.getNavn() + ": "+ kon.getSaldo());
					}
			}
		}
		finally {
		}
	}
}
