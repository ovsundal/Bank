package test;

public class TestEJB {
	
	static PersonTest pt;
	static KontoTest kt;
	static CardTest ct;
	static MinibankTest mbt;
	
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
		System.out.println("Creating 1 account for ove and 3 accounts (all empty) for Knut using Accounts-bean from KontoTest:");
		createAccounts();
		
		System.out.println("Searching for an account using get method. Should return one Account:");
		getAccount();
		
		System.out.println("Person list should now be updated with account numbers ( Ove: 1, Knut: 3): ");
		listPersons();
		
		//tests from Card-bean
		System.out.println("Add a card to two accounts. List cards:");
		addCard();
		listCards();
		
		System.out.println("Cards generated belongs to an account: ");
		listAccounts();
		
		//tests from minibank-bean
		System.out.println("--Depositing money into accounts--");
		depositMoney(2000);
		System.out.println("Accounts should now have money:");
		listAccounts();
		System.out.println("--Try to deposit a negative amount of money into accounts--");
		depositNegativeMoney(-500);
		System.out.println("Accounts should be unchanged:");
		listAccounts();
		
		System.out.println("--Withdrawing 500 NOK from all accounts--");
		withdrawMoney(500);
		System.out.println("Accounts should now have 500 NOK less:");
		listAccounts();
		
		System.out.println("--Try to withdraw 235 NOK (non-divisable by 100). Should not work, balance should be equal to above");
		withdrawMoney(235);
		listAccounts();
		
		System.out.println("--Try to withdraw 5000 NOK (should only work for the last two of the accounts)--");
		withdrawMoney(5000);
		listAccounts();
		
		System.out.println("--Try to withdraw -1000 NOK (negative amount, should not work for any account)--");
		withdrawMoney(-1000);
		listAccounts();
		
		System.out.println("Transfer 1000 NOK from account 4 to account 1:");
		transferMoney(1000);
		listAccounts();
		
		System.out.println("--Create a new account to ove using MiniBank-bean createAccount--");
		createAccount();
		System.out.println("Should now have a crypto-account");
		listAccounts();
		
		
		
		
		
		
		
		cleanUpTests();
		
		
		
		
	
	
		
	}
	

	


	public static void setupTests() throws Exception {

		pt = new PersonTest();
		kt = new KontoTest();
		ct = new CardTest();
		mbt = new MinibankTest();
		System.out.println("Setting up remote beans...");
		pt.setUp();
		kt.setUp();
		ct.setUp();
		mbt.setUp();
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
	
	private static void getAccount() {

			kt.getAccount();
			System.out.println("------------------------------------");
	}
	
	private static void listAccounts() {
		try {
			kt.list();
			System.out.println("------------------------------------");
		} catch (Exception e) {
			System.out.println("Error, could not list accounts from database");
			e.printStackTrace();
		}
	}
	
	private static void getBalance() {
		kt.getBalance();
		System.out.println("------------------------------------");
	}
	
	private static void addCard() {
		ct.addCard();
		System.out.println("------------------------------------");
	}
	
	private static void listCards() {
		try {
			ct.list();
			System.out.println("------------------------------------");
		} catch (Exception e) {
			System.out.println("Error, could not list cards from database");
			e.printStackTrace();
		}
	}
	
	private static void depositMoney(int amount) {
		
		mbt.depositMoney(amount);
	}
	
	private static void depositNegativeMoney(int amount) {
		mbt.depositNegativeMoney(amount);
	}
	
	private static void withdrawMoney(int amount) {
		mbt.withdrawMoney(amount);
		
	}

	private static void transferMoney(int amount) {
		mbt.transferMoney(amount);
		
	}
	
	private static void createAccount() {
		mbt.createAccount();
		
	}
	

	public static void cleanUpTests() throws Exception {
		System.out.println("Cleaning up tests..");
		pt.remove();
		kt.remove();
		ct.remove();
		pt.tearDown();
		kt.tearDown();
		ct.tearDown();
		System.out.println("Cleanup complete!");
	}
}
