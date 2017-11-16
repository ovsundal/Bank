package test;
import java.util.*;
import javax.ejb.EJB;
import javax.naming.*;
import java.util.Properties;
import junit.framework.TestCase;
import entity.*;
import sessionsBeans.*;

public class PersonTest extends TestCase {
	Context context;
	@EJB
	// Enheten vi opererer p�
	PersonerRemote personer;
	
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
		personer= (PersonerRemote)context.lookup("PersonerRemote");
	}
	
	@Override
	public void tearDown() throws Exception {}
	
	public void test() throws Exception {
		List<Person> list = personer.list();
		int assertsize = list.size();
		assertEquals( "List.size()", assertsize, list.size());
		addPersons();
		list = personer.list();
		assertEquals( "List.size()", assertsize + 4, list.size());
		list();
	}
	
	public void addPersons() throws Exception {
		try{
//			Calendar c = Calendar.getInstance();
//			c.set(1988, 00, 00, 01, 01, 01);
			Person p = new Person("01020304056", "Ove Sundal", 
					"Tastatunet 22","", "4027", "Stavanger");
			personer.add(p);
			
			p = new Person("12345678910", "Kristin Hagen", 
					"Tastatunet 22","", "8515", "Narvik");
			personer.add(p);
			
			p = new Person("33332223344", "Knut Lurendreier", "Luregaten 22", 
					"Trikseveien 14","7815", "Lureland");
			personer.add(p);
			}
		finally{
		}
	}

	
	public void list() throws Exception {
		List<Person> list = personer.list();
		for(Person pers : list) {
			System.out.println("Listing : "+ pers.getPersonnummer() + " -" + 
					pers.getNavn() + ": "+ pers.getAdresselinje_1() + pers.getPostnummer());
		}
	}
	
	public void remove() throws Exception {
		try{
			List<Person> list = personer.list();
			for(Person pers : list) {
				if(pers.getId() > 0) {
					personer.remove(pers);
					System.out.println("Deleting ... : " + pers.getPersonnummer() + " -" + 
					pers.getNavn() + ": "+ pers.getAdresselinje_1() + pers.getPostnummer());
					}
				else {
					System.out.println("Delete failed ... : " + pers.getPersonnummer() + " -" + 
							pers.getNavn() + ": "+ pers.getAdresselinje_1() + pers.getPostnummer());
					}
			}
		}
		finally {
		}
	}
}

				





























