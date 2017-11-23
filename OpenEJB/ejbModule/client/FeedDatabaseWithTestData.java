package client;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Account;
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
	}

	public static void main(String[] args) {

		try {
			setUp();

			// add persons
			Person p1 = new Person("05118079512", "Ola Nordmann", "Lykkebakken 12", "R101", "4859", "Lykkeland");
			Person p2 = new Person("13029180123", "Kari Larsen", "Rundetorget 44", "R223", "7047", "Hageland");
			persons.add(p1);
			persons.add(p2);

			// get old dates for accounts
			Date date1 = getDate(2011, 04, 12);
			Date date2 = getDate(2014, 07, 21);

			// add accounts
			Account a1 = new Account(p1, "Forbrukskonto", 0, date1);
			Account a2 = new Account(p2, "Feriekonto", 0, date2);
			
			accounts.add(a1);
			accounts.add(a2);
			
			//add cards
			
			
			//add account creation to log
			
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
