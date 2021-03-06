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
import sessionBeans.AccountsRemote;
import sessionBeans.PersonsRemote;

/**
 * Test methods for Account-bean
 * 
 * @author Ove
 *
 */
public class AccountTest {
	Context context;
	@EJB
	AccountsRemote accounts;
	@EJB
	PersonsRemote persons;

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
		accounts = (AccountsRemote) context.lookup("AccountsRemote");
		persons = (PersonsRemote) context.lookup("PersonsRemote");
	}

	public void addKonto() throws Exception {
		try {
			// query a person (Ove from database and create one account
			String personnummer = "01020304056";
			Person ove = persons.get(personnummer);

			Calendar d = Calendar.getInstance();

			// add an account to person
			Account k = new Account(ove, "Forbrukskonto", 0, d);
			accounts.add(k);

			// query a person (Knut) from database and create three accounts:
			personnummer = "33332223344";
			Person knut = persons.get(personnummer);

			k = new Account(knut, "Gjeldskonto", 0, d);
			accounts.add(k);
			k = new Account(knut, "Midtlivskrisekonto", 0, d);
			accounts.add(k);
			k = new Account(knut, "Luksuskonto", 0, d);
			accounts.add(k);
		} finally {
		}
	}

	public void list() throws Exception {
		List<Account> list = accounts.list();
		for (Account kon : list) {
			System.out.println("Listing : " + kon.toString());
		}
	}

	public void remove() throws Exception {
		try {
			List<Account> list = accounts.list();
			for (Account kon : list) {
				if (kon.getId() > 0) {
					accounts.remove(kon);
					System.out.println("Deleting ... : " + kon.toString());
				} else {
					System.out.println("Delete failed ... : " + kon.toString());
				}
			}
		} finally {
		}
	}

	/**
	 * Returns the balance of all created accounts
	 */
	public void getBalance() {

		try {
			List<Account> list = accounts.list();
			for (Account acc : list) {
				System.out.println(
						"Balance: " + accounts.getAccountBalance(acc.getId()) + " for account: " + acc.toString());
			}
		} catch (Exception e) {
			System.out.println("ERROR, could not get balance");
			e.printStackTrace();
		}
	}

	public void getAccount() {
		try {
			List<Account> list = accounts.list();
			Account acc = list.get(0);
			System.out.println("Should find this account: " + acc.toString());

			Account accFound = accounts.get(acc.getId());
			System.out.println("Found this account: " + accFound.toString());
		} catch (Exception e) {
			System.out.println("ERROR, could not find account using method get from accounts-bean");
		}

	}
}
