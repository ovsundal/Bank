package test;

public class TestEJB {
	
	static PersonTest pt;
	static KontoTest kt;
	
	public static void main(String[] args) throws Exception {

		setupTests();
		
		//tests from Personer-bean
		System.out.println("Creating 3 persons using Persons-bean from PersonTest...");
		addPersons();
		
		String personnummerToFind = "33332223344";
		System.out.println("Searching for a person based on personnummer(" + personnummerToFind + ") using Personer-bean from PersonTest: ");
		findPerson(personnummerToFind);
		
		String personnummerToRemove = "12345678910";
		System.out.println("Removing a person from database based on personnummer +(" + personnummerToRemove + ") using Personer-bean from PersonTest:");
		removePerson(personnummerToRemove);
		
		System.out.println("Should now be 2 persons in database listing from Personer-bean from PersonTest:");
		listPersons();
		
//		//tests from Accounts-bean
		System.out.println("Creating 1 account for ove and 3 accounts for Knut using Accounts-bean from KontoTest:");
		createAccounts();
		
		System.out.println("Person list should now be updated with account numbers ( Ove: 1, Knut: 3): ");
		listPersons();
		
		System.out.println("Get the current balance for all the four accounts: " );
		getBalance();
		
		
		
		
		cleanUpTests();
		
		
		
		
//		doTest();
		
		
	
	
		
	}
	
	

	public static void setupTests() throws Exception {

		pt = new PersonTest();
		kt = new KontoTest();
		System.out.println("Setting up remote beans...");
		pt.setUp();
		kt.setUp();
	}

	private static void addPersons() {
		
		try {
			pt.addPersons();
			System.out.println("Three persons were added to database:");
			System.out.println("------------------------------------");
			pt.list();
			System.out.println("------------------------------------");
		} catch (Exception e) {
			System.out.println("ERROR: Could not add persons");
			e.printStackTrace();
		}
	}
	
	private static void findPerson(String personnummer) {
		try {
			pt.findPerson(personnummer);
			System.out.println("------------------------------------");
		} catch(NullPointerException e) {
			System.out.println("Error, could not find any persons");
		}
		catch(Exception e) {
			System.out.println("Error, could not find person");
		}
	}
	
	private static void removePerson(String personnummer) {
		
		pt.removePerson(personnummer);
	}
	
	private static void listPersons() {
		try {
			pt.list();
			System.out.println("------------------------------------");
		} catch (Exception e) {
			System.out.println("Error, could not list persons from database");
			e.printStackTrace();
		}
	}
	
	private static void createAccounts() {
		try {
			kt.addKonto();
			kt.list();
			System.out.println("------------------------------------");
		} catch (Exception e) {
			System.out.println("Error, could not create accounts");
			e.printStackTrace();
		}
	}
	
	private static void getBalance() {
		kt.getBalance();
		System.out.println("------------------------------------");
	}
	

	public static void cleanUpTests() throws Exception {
		System.out.println("Cleaning up tests..");
		pt.remove();
		kt.remove();
		
		pt.tearDown();
		kt.tearDown();
		System.out.println("Cleanup complete!");
	}
}