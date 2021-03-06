package client;

import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Account;
import entity.Card;
import entity.Person;
import sessionBeans.AccountsRemote;
import sessionBeans.CardsRemote;
import sessionBeans.MiniBankRemote;
import sessionBeans.PersonsRemote;

/**
 * This class injects minibank test data into the database. 
 * Used prior to launching client class.
 * 
 * IMPORTANT - once this class is run, make a note of 
 * the console messages that contains two card- and pin
 * number
 * @author Ove
 *
 */
public class FeedDatabaseWithTestData {
	static Context context;
	@EJB
	static AccountsRemote accounts;
	@EJB
	static PersonsRemote persons;
	@EJB
	static CardsRemote cards;
	@EJB
	static MiniBankRemote minibank;

	public static void setUp() throws Exception {
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
		cards = (CardsRemote) context.lookup("CardsRemote");
		accounts = (AccountsRemote) context.lookup("AccountsRemote");
		persons = (PersonsRemote) context.lookup("PersonsRemote");
		minibank = (MiniBankRemote) context.lookup("MiniBankRemote");
	}

	public static void main(String[] args) {

		try {
			setUp();

			// add persons
			Person person1 = new Person("05118079512", "Ola Nordmann", "Lykkebakken 12", "R101", "4859", "Lykkeland");
			Person person2 = new Person("13029180123", "Kari Larsen", "Rundetorget 44", "R223", "7047", "Hageland");
			persons.add(person1);
			persons.add(person2);
			
			//get person with id from database
			String personNumber1 = "05118079512";
			String personNumber2 = "13029180123";
			
			Person p1 = persons.get(personNumber1);
			Person p2 = persons.get(personNumber2);
			
			//create old dates for accounts
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.set(2011, 04, 12);
			cal2.set(2014, 07, 21);
			
			// add accounts
			Account account1 = new Account(p1, "Forbrukskonto", 0, cal1);
			Account account2 = new Account(p2, "Feriekonto", 0, cal2);
			accounts.add(account1);
			accounts.add(account2);
			
			//get accounts with database id
			List<Account> a1List = accounts.getOwnedAccounts(p1.getId());
			List<Account> a2List = accounts.getOwnedAccounts(p2.getId());
			Account a1 = a1List.get(0);
			Account a2 = a2List.get(0);
			
			//add cards to accounts
			Card c1 = new Card(a1);
			Card c2 = new Card(a2);
			cards.add(c1);
			cards.add(c2);
			//print card and pin numbers to console
			System.out.println(c1.greet());
			System.out.println(c2.greet());
			
			//do some deposits, withdrawals and transfers...
			
			//create old dates for deposits into account 1
			Calendar d1 = Calendar.getInstance();
			Calendar d2 = Calendar.getInstance();
			Calendar d3 = Calendar.getInstance();
			Calendar d4 = Calendar.getInstance();
			
			
			d1.set(2011, 04, 13);
			d2.set(2013, 12, 18);
			d3.set(2015, 02, 07);
			d4.set(2016, 07, 22);
			
			//create old dates for deposits into account 2
			Calendar d5 = Calendar.getInstance();
			Calendar d6 = Calendar.getInstance();
			Calendar d7 = Calendar.getInstance();
			Calendar d8 = Calendar.getInstance();
			
			
			d5.set(2014, 07, 22);
			d6.set(2014, 10, 19);
			d7.set(2015, 01, 29);
			d8.set(2017, 06, 21);

			//create old dates for withdrawals into account 1
			
			Calendar w1 = Calendar.getInstance();
			Calendar w2 = Calendar.getInstance();
			Calendar w3 = Calendar.getInstance();
			Calendar w4 = Calendar.getInstance();
			
			w1.set(2011, 10, 18);
			w2.set(2014, 03, 15);
			w3.set(2015, 07, 07);
			w4.set(2017, 12, 05);
			
			//create old dates for withdrawals into account 2
			Calendar w5 = Calendar.getInstance();
			Calendar w6 = Calendar.getInstance();
			
			w5.set(2015, 11, 30);
			w6.set(2017, 02, 01);

			//do deposits and withdraws from account 1 (in correct timeline)
			
			minibank.deposit(a1.getId(), 1000, d1);
			minibank.withdraw(a1.getId(), 200, w1);
			minibank.deposit(a1.getId(), 7431, d2);
			minibank.withdraw(a1.getId(), 900, w2);
			minibank.deposit(a1.getId(), 15000, d3);
			minibank.withdraw(a1.getId(), 2000, w3);
			minibank.deposit(a1.getId(), 14782, d4);
			minibank.withdraw(a1.getId(), 3500, w4);

			//do deposits and withdraws from account 2 (in correct timeline)
			minibank.deposit(a2.getId(), 784, d5);
			minibank.deposit(a2.getId(), 2513, d6);
			minibank.deposit(a2.getId(), 8569, d7);
			minibank.withdraw(a2.getId(), 400, w5);
			minibank.withdraw(a2.getId(), 1700, w6);
			minibank.deposit(a2.getId(), 19000, d8);

			System.out.println("Data successfully added to database");
			
		} catch (Exception e) {
			System.out.println("ERROR, could not add testdata. Delete the database and try again");
			e.printStackTrace();
		}
	}
}
