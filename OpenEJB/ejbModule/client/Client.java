package client;

import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Account;
import entity.Log;
import entity.Person;
import sessionBeans.MiniBankRemote;

/**
 * This class simulates the minibank a customer interacts with. All remote
 * requests are done using MiniBank class in package sessionsBeans.
 * 
 * I am making the assumption that the minibank does NOT have a keyboard capable
 * of typing letters, methods therefore do not have any protection against
 * illegal character injection
 * 
 * @author Ove
 *
 */
public class Client {

	static MiniBankRemote minibank;
	static List<Account> accountList;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// setup client
		try {
			setUpClient();
		} catch (Exception e) {
			System.out.println("ERROR, could not connect to remote beans");
			e.printStackTrace();
		}

		boolean loggedIn = false;

		// greetings menu
		while (true) {

			// validate card number and PIN
			System.out.println("*************************************************************");
			System.out.println("Welcome to HBF MiniBank! Please login using your card number:");
			String cardNumber = sc.nextLine().trim();
			if (minibank.validateCardNumber(cardNumber)) {
				System.out.println("Enter PIN");
				String pin = sc.next().trim();
				if (minibank.validatePin(cardNumber, pin)) {
					// if you got here, login was successful. Retrieve accountId
					// and person identity
					loggedIn = true;
					int accountId = minibank.getAccountId(cardNumber);
					Person p = minibank.getAccountOwner(accountId);

					System.out.println("Welcome back " + p.getName());
					// keep running menu until user logs out
					while (loggedIn) {

						// logged in greetings menu
						System.out.println("************************");
						System.out.println("1. View account balance");
						System.out.println("2. Make a deposit");
						System.out.println("3. Make a withdrawal");
						System.out.println("4. Make transfer");
						System.out.println("5. Create new account");
						System.out.println("6. List all owned accounts");
						System.out.println("7. Get account statement");
						System.out.println("8. Logout");
						System.out.println("************************\n");
						System.out.print("Select a task: ");
						int userChoice = sc.nextInt();

						switch (userChoice) {

						// view account balance
						case 1:
							System.out.println(minibank.getAccountBalance(accountId));
							continue;
						// deposit money
						case 2:
							System.out.print("Enter amount to deposit: ");
							int amountForDeposit = sc.nextInt();
							// abort if amount is 0 or negative
							if (amountForDeposit <= 0) {
								System.out.println("You cannot deposit a negative amount. Aborted");
								continue;
							}
							minibank.deposit(accountId, amountForDeposit);
							System.out.println(
									"Deposited " + amountForDeposit + ". New " + minibank.getAccountBalance(accountId));
							continue;
						// withdraw money
						case 3:
							System.out.print("Enter amount to withdraw: ");
							int amountForWithdrawal = sc.nextInt();

							// abort if amount is 0 or negative
							if (amountForWithdrawal <= 0) {
								System.out.println("You cannot withdraw a negative amount. Aborted");
								continue;
							}
							// abort if amount is not divisible by 100
							if (amountForWithdrawal % 100 != 0) {
								System.out.println("Amount must be rounded to nearest 100. Transaction aborted.");
								continue;
							}
							minibank.withdraw(accountId, amountForWithdrawal);
							System.out.println("Withdrew " + amountForWithdrawal + ". New "
									+ minibank.getAccountBalance(accountId));
							continue;
						// transfer money
						case 4:
							System.out.println(doTransfer(p.getId(), accountId));
							continue;
						// create new account
						case 5:
							System.out.print("Enter account name: ");
							String accountName = sc.next();
							System.out.println("Account generated\n"
									+ minibank.createAccount(p.getPersonId(), accountName, 0, Calendar.getInstance()));
							continue;
						// List all accounts owned by person
						case 6:
							System.out.println("Your accounts:\n");
							accountList = getAccountList(p.getId());
							for (Account item : accountList) {
								System.out.println("Listing : " + item.toString());
							}
							continue;
						// get account statement
						case 7:
							getAccountStatement(accountId);
							continue;
						// logout
						case 8:
							System.out.println("Have a nice day!");
							loggedIn = false;
							break;
						}
					}

				}
			} else {
				System.out.println("Could not recognize card number, please try again");
				continue;
			}
		}

	}

	private static void getAccountStatement(int accountId) {
		// request FROM period
		System.out.println("Get account statement FROM WHEN? (ddmmyyyy)");
		String from = sc.next();

		// set dates from user input into calendar
		Integer fromYear = Integer.valueOf(from.substring(4));
		Integer fromMonth = Integer.valueOf(from.substring(2, 4)) - 1;
		Integer fromDay = Integer.valueOf(from.substring(0, 2));

		Calendar calFrom = Calendar.getInstance();
		calFrom.set(fromYear, fromMonth, fromDay, 0, 0, 0);

		// request TO period
		System.out.println("Get account statement TO WHEN? (ddmmyyyy)");
		String to = sc.next();

		// set dates from user input into calendar
		Integer toYear = Integer.valueOf(to.substring(4));
		Integer toMonth = Integer.valueOf(to.substring(2, 4)) - 1;
		Integer toDay = Integer.valueOf(to.substring(0, 2));

		Calendar calTo = Calendar.getInstance();
		calTo.set(toYear, toMonth, toDay, 0, 0, 0);

		// get account log
		List<Log> listOfLogs = minibank.showLog(accountId);

		// filter log and list relevant statements
		System.out.println("Showing account balance from " + calFrom.getTime() + " to " + calTo.getTime());
		for (Log l : listOfLogs) {
			if (l.getDate().getTime().after(calFrom.getTime()) && l.getDate().getTime().before(calTo.getTime())
					&& l.getThisAccountId() == accountId) {
				System.out.println(l.toString());
			}
		}
	}

	private static String doTransfer(int personId, int accountId) {
		// get available accounts for transferring
		boolean transferWasDone = false;
		String response = "";
		System.out.print("Your available accounts are:\n");
		accountList = getAccountList(personId);
		for (Account item : accountList) {
			System.out.println("Listing : " + item.toString());
		}
		// do transfer
		System.out.print("Transfer from this account to which? (enter id number of account)");
		int transferTo = sc.nextInt();
		// validate transfer-to account id
		for (Account item : accountList) {
			// if found, go ahead with transaction
			if (transferTo == item.getId()) {
				System.out.println("Enter transfer amount; ");
				int amount = sc.nextInt();
				// abort if amount is 0 or negative
				if (amount <= 0) {
					System.out.println("You cannot transfer a negative amount. Aborted.");
					break;
				}
				response = minibank.transfer(accountId, transferTo, amount);
				transferWasDone = true;
				break;
			}
		}
		// if used input account was not found, notify user and abort
		if (!transferWasDone) {
			return "Error, could not do transfer. Try again";
		} else {
			return response;
		}
	}

	/**
	 * Returns all accounts owned by person
	 * 
	 * @param personId
	 * @return
	 */
	public static List<Account> getAccountList(int personId) {

		return minibank.getAccounts(personId);
	}

	public static void setUpClient() throws Exception {

		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
		p.put("BankDBDatasource", "new://Resource?type=DataSource");
		p.put("BankDBDatasource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("BankDBDatasource.JdbcUrl", "jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource", "new://Resource?type=DataSource");
		p.put("Unmanaged_BankDBDataSource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("Unmanaged_BankDBDataSource.JdbcUrl", "jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource.JtaManaged", "false");
		Context context = new InitialContext(p);
		minibank = (MiniBankRemote) context.lookup("MiniBankRemote");
	}
}