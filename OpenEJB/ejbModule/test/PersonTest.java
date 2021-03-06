package test;

import java.util.*;
import javax.ejb.EJB;
import javax.naming.*;
import java.util.Properties;
import junit.framework.TestCase;
import sessionBeans.*;
import entity.*;

/**
 * Test methods for Person-bean
 * 
 * @author Ove
 *
 */
public class PersonTest {
	Context context;
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
		persons = (PersonsRemote) context.lookup("PersonsRemote");
	}

	/**
	 * Feed the database with test data
	 * 
	 * @throws Exception
	 */
	public void addPersons() throws Exception {
		try {
			Person p = new Person("01020304056", "Ove Sundal", "Tastatunet 22", "", "4027", "Stavanger");
			persons.add(p);

			p = new Person("12345678910", "Kristin Hagen", "Tastatunet 22", "", "8515", "Narvik");
			persons.add(p);

			p = new Person("33332223344", "Knut Lurendreier", "Luregaten 22", "Trikseveien 14", "7815", "Lureland");
			persons.add(p);
		} finally {
		}
	}

	/**
	 * Retrieve a person from database using personnummer as identifier
	 * 
	 * @throws Exception
	 */
	public void findPerson(String personnummer) {

		try {
			Person pers = persons.get(personnummer);

			System.out.println(pers.toString());

		} catch (NullPointerException e) {
			System.out.println("ERROR, could not find person with this personnummer");
		} catch (Exception e) {
			System.out.println("ERROR, something went wrong: ");
			e.printStackTrace();
		}
	}

	/**
	 * Remove a person from the database
	 */
	public void removePerson(String personnummer) {

		Person p;
		try {
			p = persons.get(personnummer);
			persons.remove(p);
		} catch (Exception e) {
			System.out.println("Error, could not delete person with personnummer " + personnummer);
			e.printStackTrace();
		}

	}

	/**
	 * List queried persons from the database
	 * 
	 * @throws Exception
	 */
	public void list() throws Exception {
		List<Person> list = persons.list();

		for (Person pers : list) {
			System.out.println(pers.toString());
		}
	}

	/**
	 * Remove all remaining persons from the database
	 * 
	 * @throws Exception
	 */
	public void remove() throws Exception {
		try {
			List<Person> list = persons.list();
			for (Person pers : list) {
				if (pers.getId() > 0) {
					persons.remove(pers);
					System.out.println("Deleting ... : " + pers.getPersonId() + " -" + pers.getName() + ": "
							+ pers.getAddress_1() + pers.getAreaCode());
				} else {
					System.out.println("Delete failed ... : " + pers.getPersonId() + " -" + pers.getName() + ": "
							+ pers.getAddress_1() + pers.getAreaCode());
				}
			}
		} finally {
		}
	}
}
