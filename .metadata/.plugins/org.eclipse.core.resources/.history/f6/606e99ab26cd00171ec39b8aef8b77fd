package test;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Account;
import junit.framework.TestCase;
import sessionsBeans.AccountsRemote;
import sessionsBeans.MiniBankRemote;
import sessionsBeans.PersonsRemote;

public class MiniBankTest extends TestCase {

	Context context;
	@EJB
	MiniBankRemote minibank;
	AccountsRemote accounts;

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
		minibank = (MiniBankRemote) context.lookup("MiniBankRemote");
		accounts = (AccountsRemote) context.lookup("AccountsRemote");
	}
	
	@Override
	public void tearDown() throws Exception {}

	/**
	 * Deposits money in all test accounts
	 */
	public void depositMoney() {
		
		try {
			int moneyForDeposit = 2000;
			
			List<Account> list = accounts.list();
			for(Account acc : list) {
				minibank.deposit(acc, moneyForDeposit);
				moneyForDeposit += 2000;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
