package client;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Account;
import entity.Card;
import entity.Person;
import sessionsBeans.AccountsRemote;
import sessionsBeans.CardsRemote;
import sessionsBeans.MiniBankRemote;
import sessionsBeans.PersonsRemote;

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

			// create old dates for accounts
//			Date date1 = getDate(2011, 04, 12);
//			Date date2 = getDate(2014, 07, 21);
			
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
			List<Account> a1List = accounts.acquireFromPerson(p1.getId());
			List<Account> a2List = accounts.acquireFromPerson(p2.getId());
			Account a1 = a1List.get(0);
			Account a2 = a2List.get(0);
			
			//add cards
			Card c1 = new Card(a1);
			Card c2 = new Card(a2);
			cards.add(c1);
			cards.add(c2);
			System.out.println(c1.greet());
			System.out.println(c2.greet());
			
			//do some deposits, withdrawals and transfers
			
			//create old dates for deposits into account 1
			Calendar d1 = Calendar.getInstance();
			Calendar d2 = Calendar.getInstance();
			Calendar d3 = Calendar.getInstance();
			Calendar d4 = Calendar.getInstance();
			
			d1.set(2011, 04, 13);
			d2.set(2013, 12, 18);
			d3.set(2015, 02, 07);
			d4.set(2016, 07, 22);
			
//			Date deposit1 = getDate(2011, 04, 13);
//			Date deposit2 = getDate(2013, 12, 18);
//			Date deposit3 = getDate(2015, 02, 07);
//			Date deposit4 = getDate(2016, 07, 22);
			
			//create old dates for deposits into account 2
			Calendar d5 = Calendar.getInstance();
			Calendar d6 = Calendar.getInstance();
			Calendar d7 = Calendar.getInstance();
			Calendar d8 = Calendar.getInstance();
			
			d5.set(2014, 07, 22);
			d6.set(2014, 10, 19);
			d7.set(2015, 01, 29);
			d8.set(2017, 06, 21);
			
//			Date deposit5 = getDate(2014, 07, 22);
//			Date deposit6 = getDate(2014, 10, 19);
//			Date deposit7 = getDate(2015, 01, 29);
//			Date deposit8 = getDate(2017, 06, 21);
			
			//deposit into account 1
			minibank.deposit(a1.getId(), 1000, d1);
			minibank.deposit(a1.getId(), 7431, d2);
			minibank.deposit(a1.getId(), 15000, d3);
			minibank.deposit(a1.getId(), 14782, d4);
			
			//deposit into account 2
			minibank.deposit(a2.getId(), 784, d5);
			minibank.deposit(a2.getId(), 2513, d6);
			minibank.deposit(a2.getId(), 8569, d7);
			minibank.deposit(a2.getId(), 19000, d8);

			//create old dates for withdrawals into account 1
			
			Calendar w1 = Calendar.getInstance();
			Calendar w2 = Calendar.getInstance();
			Calendar w3 = Calendar.getInstance();
			Calendar w4 = Calendar.getInstance();
			
			w1.set(2011, 10, 18);
			w2.set(2014, 03, 15);
			w3.set(2015, 07, 07);
			w4.set(2017, 12, 05);
			
//			Date withdraw1 = getDate(2011, 10, 18);
//			Date withdraw2 = getDate(2014, 03, 15);
//			Date withdraw3 = getDate(2015, 07, 07);
//			Date withdraw4 = getDate(2016, 12, 05);
			
			//create old dates for withdrawals into account 2
			Calendar w5 = Calendar.getInstance();
			Calendar w6 = Calendar.getInstance();
			
			w5.set(2015, 11, 30);
			w6.set(2017, 02, 01);
			
			
//			Date withdraw5 = getDate(2015, 11, 30);
//			Date withdraw6 = getDate(2017, 02, 01);
			
			//withdraw from account 1
			minibank.withdraw(a1.getId(), 200, w1);
			minibank.withdraw(a1.getId(), 900, w2);
			minibank.withdraw(a1.getId(), 2000, w3);
			minibank.withdraw(a1.getId(), 3500, w4);

			//withdraw from account 2
			minibank.withdraw(a2.getId(), 400, w5);
			minibank.withdraw(a2.getId(), 1700, w6);
			
			//create old dates for transfer 
			
			Calendar t1 = Calendar.getInstance();
			Calendar t2 = Calendar.getInstance();
			
			t1.set(2016, 04, 28);
			t2.set(2017, 01, 07);
			
//			Date transfer1 = getDate(2016, 04, 28);
//			Date transfer2 = getDate(2017, 01, 07);
			
			//transfer between accounts
			minibank.transfer(a1.getId(), a2.getId(), 1400, t1);
			minibank.transfer(a2.getId(), a1.getId(), 470, t2);
			
			System.out.println("Data successfully added to database");
			
		} catch (Exception e) {
			System.out.println("ERROR, could not add testdata");
			e.printStackTrace();
		}

	}

	static Date getDate(int year, int month, int day) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		// date from
		LocalDate date = LocalDate.of(2017, 01, 01);

		Date dateFrom = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return dateFrom;
	}

}
