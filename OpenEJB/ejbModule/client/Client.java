package client;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Account;
import entity.Log;
import entity.Person;
import sessionsBeans.MiniBankRemote;
import test.CardTest;
import test.KontoTest;
import test.LogTest;
import test.MinibankTest;
import test.PersonTest;

public class Client {

	static MiniBankRemote minibank;
	static List<Account> accountList;

	public static void main(String[] args) {

		try {
			setUpClient();
		} catch (Exception e) {
			System.out.println("ERROR, could not connect to remote beans");
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		boolean loggedIn = false;

		while (true) {

			// validate card number and PIN
			System.out.println("*************************************************************");
			System.out.println("Welcome to HBF MiniBank! Please login using your card number:");
			String cardNumber = sc.nextLine().trim();
			if (minibank.validateCardNumber(cardNumber)) {
				System.out.println("Enter PIN");
				String pin = sc.next().trim();
				if (minibank.validatePin(cardNumber, pin)) {
					// login successful, get accountId
					loggedIn = true;
					int accountId = minibank.getAccountId(cardNumber);
					Person p = minibank.getAccountOwner(accountId);
					
					System.out.println("Welcome back " + p.getName());
					// keep running menu until user logs out
					while (loggedIn) {

						// get account based on card number
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

						case 1:

							System.out.println(minibank.getAccountBalance(accountId));
							continue;
						case 2:
							System.out.print("Enter amount to deposit: ");
							int amountForDeposit = sc.nextInt();
							minibank.deposit(accountId, amountForDeposit);
							System.out.println("Deposited " + amountForDeposit + ". New " +  minibank.getAccountBalance(accountId));
							continue;
						case 3:
							System.out.print("Enter amount to withdraw: ");
							int amountForWithdrawal = sc.nextInt();
							//todo: must be % 100 = 0
							minibank.withdraw(accountId, amountForWithdrawal);
							System.out.println("Withdrew " + amountForWithdrawal + ". New " + minibank.getAccountBalance(accountId));
							continue;
						case 4:
							//list available accounts 
							System.out.print("Your available accounts are: ");
							accountList = getAccountList(p.getId());
							for(Account item : accountList) {
								System.out.println("Listing : " + item.toString());
							}
							//do transfer
							System.out.print("Transfer from this account to which? (enter id number of account)");
							int transferTo = sc.nextInt();
							System.out.println("Enter transfer amount; ");
							int amount = sc.nextInt();
							System.out.println(minibank.transfer(accountId, transferTo, amount));
							continue;
						case 5:
							System.out.print("Enter account name: ");
							String accountName = sc.next();
							System.out.println("Account generated" + minibank.createAccount(p.getPersonId(), accountName, 0, new Date()));
							continue;
						case 6:
							System.out.println("Your accounts:");
							accountList = getAccountList(p.getId());
							for(Account item : accountList) {
								System.out.println("Listing : " + item.toString());
							}
							continue;
							//account statement
						case 7:
							//request FROM-TO period
							System.out.println("Get account statement FROM WHEN? (ddmmyyyy)");
							String from = sc.next();
							System.out.println("Get account statement TO WHEN? (ddmmyyyy)");
							String to = sc.next();
							
							//get dates from user input
							int fromYear = Integer.valueOf(from.substring(4));
							int fromMonth = Integer.valueOf(from.substring(2, 3));
							int fromDay = Integer.valueOf(from.substring(0, 1));
							
							int toYear = Integer.valueOf(to.substring(4));
							int toMonth = Integer.valueOf(to.substring(2, 3));
							int toDay = Integer.valueOf(to.substring(0, 1));
							
							//convert user input to Date
							LocalDate dateF = LocalDate.of( fromYear,fromMonth,fromDay);
							LocalDate dateT = LocalDate.of( toYear,toMonth,toDay);
							
							Date dateFrom = Date.from(dateF.atStartOfDay(ZoneId.systemDefault()).toInstant());
							Date dateTo = Date.from(dateT.atStartOfDay(ZoneId.systemDefault()).toInstant());
							
							//get account log
							List<Log> listOfLogs = minibank.showLog(accountId);
							System.out.println("got this list: " + listOfLogs);
							
							//filter log and list statements
							System.out.println("Showing account balance from " + fromYear + "." + fromMonth + "." + fromDay +
									" to " + toYear + "." + toMonth + "." + toDay);
							for (Log l : listOfLogs) {
								if(l.getDate().after(dateFrom) && l.getDate().before(dateTo)) {
									System.out.println(l.getAccountBalance());
								}
							}							
							continue;
							
							//logout
						case 8: System.out.println("Have a nice day!");
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
