package test;

import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.TestCase;
import sessionsBeans.AccountsRemote;
import sessionsBeans.CardsRemote;
import sessionsBeans.PersonsRemote;

public class CardTest extends TestCase{
	Context context;
	@EJB
	CardsRemote cards;
	AccountsRemote accounts;
	
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
		cards = (CardsRemote)context.lookup("CardsRemote");
	}
	
	@Override
	public void tearDown() throws Exception {}

	public void addCard() {

		
	}
	
	
}
