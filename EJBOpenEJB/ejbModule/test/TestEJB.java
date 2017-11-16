package test;

public class TestEJB {
	
	static PersonTest pt;
	static KontoTest kt;
	
	public static void main(String[] args) throws Exception {

		setupTests();
		
		System.out.println("Creating 3 persons...");
		addPersons();
		
//		System.out.println("Creating 0 accounts for person 1, 1 account for person 2, 2 accounts for person 3:");
//		createAccounts();
		
		System.out.println("Searching for a person based on personnummer (01020304056): ");
		findPerson();
		
		
		
		
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
	
	private static void createAccounts() {
		try {
			kt.addKonto();
			System.out.println("Accounts created: ");
			kt.list();
			System.out.println("------------------------------------");
		} catch (Exception e) {
			System.out.println("Error, could not create accounts");
			e.printStackTrace();
		}
	}
	
	private static void findPerson() {
		try {
			pt.findPerson();
			System.out.println("------------------------------------");
		} catch(NullPointerException e) {
			System.out.println("Error, could not find any persons");
		}
		catch(Exception e) {
			System.out.println("Error, could not find person");
		}
	}

	public static void doTest() throws Exception {

		PersonTest pt = new PersonTest();
		KundeImportTest kit = new KundeImportTest();
		KontoTest kt = new KontoTest();
		
//		pt.setUp();
		kit.setUp();
//		kt.setUp();
//		System.out.println("Adding persons using Personer-bean from PersonTest...");
//		pt.addPerson();
//		pt.list();
		kt.addKonto();
		System.out.println("Listing accounts: ");
		kt.list();
		System.out.println("Adding persons using KundeImport-bean from KundeImportTest...");
		kit.testNewKunde();
//		pt.list();
		pt.remove();
		kt.remove();
		pt.tearDown();
		kit.tearDown();
		kt.tearDown();
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
