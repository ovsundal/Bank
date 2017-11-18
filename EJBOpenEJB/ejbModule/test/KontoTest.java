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
import sessionsBeans.PersonerRemote;

public class KontoTest extends TestCase {
	Context context;
	@EJB
	// Enheten vi opererer p�
	KontoerRemote kontoer;
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
		kontoer= (KontoerRemote)context.lookup("KontoerRemote");
		personer = (PersonerRemote)context.lookup("PersonerRemote");
	}
	
	@Override
	public void tearDown() throws Exception {}
	
//	public void test() throws Exception {
//		List<Konto> list = kontoer.list();
//		int assertsize = list.size();
//		assertEquals( "List.size()", assertsize, list.size());
//		addKonto();
//		list = kontoer.list();
//		assertEquals( "List.size()", assertsize + 1, list.size());
//		list();
//	}
	
	public void addKonto() throws Exception {
		try{
			//query a person (Ove) from database and create one account
			String personnummer = "01020304056";
			Person ove = personer.find(personnummer);
			
			Date d = new Date();
			
			//add an account to person
			Konto k = new Konto(ove, "Forbrukskonto", "5000", d);
			kontoer.add(k);
			
			//query a person (Knut) from database and create three accounts:
			personnummer = "33332223344";
			Person knut = personer.find(personnummer);
			d = new Date();
			k = new Konto(knut, "Gjeldskonto", "19999", d);
			kontoer.add(k);
			k = new Konto(knut, "Midtlivskrisekonto", "200000", d);
			kontoer.add(k);	
			k = new Konto(knut, "Luksuskonto", "999999", d);
			kontoer.add(k);	
		} 
		finally{
		}
	}
	
	public void list() throws Exception {
		List<Konto> list = kontoer.list();
		System.out.println("List has " + list.size() + " entries");
		for(Konto kon : list) {
			System.out.println("Listing : " + kon.getOwner().getNavn() + " - " + kon.getNavn() + " - " + kon.getSaldo() + " - " + kon.getDato_opprettet());
		}
	}
	
	public void remove() throws Exception {
		try{
			List<Konto> list = kontoer.list();
			for(Konto kon : list) {
				if(kon.getId() > 0) {
					kontoer.remove(kon);
					System.out.println("Deleting ... : "+ kon.getDato_opprettet() + " - " + 
					kon.getNavn() + ": "+ kon.getSaldo());
					}
				else {
					System.out.println("Delete failed ... : "+ kon.getDato_opprettet() + " - " + 
							kon.getNavn() + ": "+ kon.getSaldo());
					}
			}
		}
		finally {
		}
	}
}
