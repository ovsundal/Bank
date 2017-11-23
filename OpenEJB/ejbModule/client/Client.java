package client;

import java.util.Properties;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;

import sessionsBeans.MiniBankRemote;
import test.CardTest;
import test.KontoTest;
import test.LogTest;
import test.MinibankTest;
import test.PersonTest;

public class Client {

	static MiniBankRemote minibank;

	public static void main(String[] args) {

		try {
			setUpClient();
		} catch (Exception e) {
			System.out.println("ERROR, could not connect to remote beans");
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);

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
					int accountId = minibank.getAccountId(cardNumber);

					// keep running menu until user logs out
					while (true) {

						// get account based on card number
						System.out.println("Select a task: ");
						System.out.println("1. View account balance");
						System.out.println("2. Make a deposit");
						System.out.println("3. Make a withdrawal");

						int userChoice = sc.nextInt();

						switch (userChoice) {

						case 1:

							System.out.println("Balance is: " + minibank.getAccountBalance(accountId));
							continue;
						case 2:
							System.out.println("Enter amount to deposit: ");
							int amountForDeposit = sc.nextInt();
							minibank.deposit(accountId, amountForDeposit);
							System.out.println("Deposited " + amountForDeposit + ". New balance is: " + minibank.getAccountBalance(accountId));
							continue;
						case 3:
							System.out.println("Enter amount to withdraw: ");
							int amountForWithdrawal = sc.nextInt();
							minibank.withdraw(accountId, amountForWithdrawal);
							System.out.println("Withdrew " + amountForWithdrawal + ". New balance is: " + minibank.getAccountBalance(accountId));
							continue;
						}
					}

				}

			} else {
				System.out.println("Could not recognize card number, please try again");
				continue;
			}
		}

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
