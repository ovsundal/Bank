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

public class MinibankTest extends TestCase {

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
	public void depositMoney(int moneyToDeposit) {
		
		try {	
			List<Account> list = accounts.list();
			for(Account acc : list) {
				minibank.deposit(acc.getId(), moneyToDeposit);
				moneyToDeposit += 2000;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Tries to deposit a negative sum
	 */
	public void depositNegativeMoney(int moneyToDeposit) {
		
		try {	
			List<Account> list = accounts.list();
			for(Account acc : list) {
				minibank.deposit(acc.getId(), moneyToDeposit);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
/**
 * Withdraw money
 * @param moneyToWithdraw
 */
	public void withdrawMoney(int moneyToWithdraw) {
		
		try {;
			
			List<Account> list = accounts.list();
			for(Account acc : list) {
				minibank.withdraw(acc.getId(), moneyToWithdraw);
			}
		} catch(Exception e) {
			System.out.println("ERROR, could not withdraw money from accounts in MiniBankTest withdrawMoney()");
		}
		
	}
	
	/**
	 * Transfer 1000 NOK from account 4 to account 1
	 * @param amount
	 */
	public void transferMoney(int amount) {
		
		try {	
			List<Account> list = accounts.list();
			minibank.transfer(list.get(3).getId(), list.get(0).getId(), amount);
		} catch (Exception e) {
			System.out.println("ERROR, could not transfer money in MiniBankTest, method transferMoney()");
			e.printStackTrace();
		}
	}

	
	
	
	
}
