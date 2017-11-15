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
	// Enheten vi opererer på
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
		addPerson();
		list = personer.list();
		assertEquals( "List.size()", assertsize + 4, list.size());
		list();
	}
	
	public void addPerson() throws Exception {
		try{
			Calendar c = Calendar.getInstance();
			c.set(1988, 00, 00, 01, 01, 01);
			Person p = new Person((Date) c.getTime(), "15251", "Gurgle Halsbrann", 
					"Jørgen Fisefins terasse 3","", "8515", "Narvik", "Harry Potter", 
					"1111","En av de virkelig store Halsbrannene i historien");
			personer.add(p);
			
			c.set(1986, 01, 01, 01, 01, 01);
			p = new Person((Date)c.getTime(), "12345", "Sylfrid Fuglesang", "Øvre Undergang 22","",
					"8515", "Narvik", "Jupp", "1111", "Kvitrende glad og positiv");
			personer.add(p);
			
			c.set(1990, 11, 11, 01, 01, 01);
			p = new Person((Date)c.getTime(), "33332", "Snork Rumlepung", "c/o Rosa Balle", 
					"Snyltestien 1","8514", "Narvik", "Money","1111","Uten sidestykke i nyere historie");
			personer.add(p);

			c.set(1991, 9, 21, 01, 01, 01);
			p = new Person((Date)c.getTime(), "54321","Walter Pengesluk","Andeby undergrunn","",
					"8500","Narvik","Donald","1111","Nær venn av Kjell Inge Røkke");
			personer.add(p);
			}
		finally{
		}
	}

	
	public void list() throws Exception {
		List<Person> list = personer.list();
		for(Person pers : list) {
			System.out.println("Listing : "+ pers.getFodselsdato() + " -" + 
					pers.getFodselsnummer() + ": "+ pers.getNavn());
		}
	}
	
	public void remove() throws Exception {
		try{
			List<Person> list = personer.list();
			for(Person pers : list) {
				if(pers.getId() > 0) {
					personer.remove(pers);
					System.out.println("Deleting ... : "+ pers.getFodselsdato() + " -" + 
					pers.getFodselsnummer() + ": "+ pers.getNavn());
					}
				else {
					System.out.println("Delete failed ... : "+ pers.getFodselsdato() + "-"+ pers.getFodselsnummer() + ": "+ pers.getNavn());
					}
			}
		}
		finally {
		}
	}
}

				





























