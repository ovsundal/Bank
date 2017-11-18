package test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Account;
import entity.Person;
import junit.framework.TestCase;
import sessionsBeans.AccountsRemote;
import sessionsBeans.PersonsRemote;

public class KontoTest extends TestCase {
	Context context;
	@EJB
	// Enheten vi opererer p�
	AccountsRemote accounts;
	PersonsRemote persons;
	
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
		accounts= (AccountsRemote)context.lookup("AccountsRemote");
		persons = (PersonsRemote)context.lookup("PersonsRemote");
	}
	
	@Override
	public void tearDown() throws Exception {}
	
//	public void test() throws Exception {
//		List<Account> list = accounts.list();
//		int assertsize = list.size();
//		assertEquals( "List.size()", assertsize, list.size());
//		addKonto();
//		list = accounts.list();
//		assertEquals( "List.size()", assertsize + 1, list.size());
//		list();
//	}
	
	public void addKonto() throws Exception {
		try{
			//query a person (Ove from database and create one account
			String personnummer = "01020304056";
			Person ove = persons.find(personnummer);
			
			Date d = new Date();
			
			//add an account to person
			Account k = new Account(ove, "Forbrukskonto", "5000", d);
			accounts.add(k);
			
			//query a person (Knut) from database and create three accounts:
			personnummer = "33332223344";
			Person knut = persons.find(personnummer);
			d = new Date();
			k = new Account(knut, "Gjeldskonto", "19999", d);
			accounts.add(k);
			k = new Account(knut, "Midtlivskrisekonto", "200000", d);
			accounts.add(k);	
			k = new Account(knut, "Luksuskonto", "999999", d);
			accounts.add(k);	
		} 
		finally{
		}
	}
	
	public void list() throws Exception {
		List<Account> list = accounts.list();
		System.out.println("List has " + list.size() + " entries");
		for(Account kon : list) {
			System.out.println("Listing : " + kon.toString());
		}
	}
	
	public void remove() throws Exception {
		try{
			List<Account> list = accounts.list();
			for(Account kon : list) {
				if(kon.getId() > 0) {
					accounts.remove(kon);
					System.out.println("Deleting ... : "+ kon.toString());
					}
				else {
					System.out.println("Delete failed ... : "+ kon.toString());
					}
			}
		}
		finally {
		}
	}
}
