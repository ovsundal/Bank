package test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Account;
import entity.Log;
import entity.Person;
import junit.framework.TestCase;
import sessionBeans.AccountsRemote;
import sessionBeans.LogsRemote;
import sessionBeans.PersonsRemote;

/**
 * Test methods for Log-bean
 * 
 * @author Ove
 *
 */
public class LogTest {
	Context context;
	@EJB
	LogsRemote logs;
	@EJB
	AccountsRemote accounts;

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
		logs = (LogsRemote) context.lookup("LogsRemote");
		accounts = (AccountsRemote) context.lookup("AccountsRemote");
	}

	public void showDepositMoneyLog() {

		try {
			List<Log> list = logs.list();
			for (Log l : list) {
				if ("deposit".equals(l.getAction())) {
					System.out.println(l.toString());
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR, could not show log from showDepositMoneyLog in LogTest");
			e.printStackTrace();
		}

	}

	public void showWithdrawMoneyLog() {

		try {
			List<Log> list = logs.list();
			for (Log l : list) {
				if ("withdraw".equals(l.getAction())) {
					System.out.println(l.toString());
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR, could not show log from showWithdrawMoneyLog in LogTest");
			e.printStackTrace();
		}
	}

	public void showTransferMoneyLog() {
		try {
			List<Log> list = logs.list();
			for (Log l : list) {
				if ("transferFrom".equals(l.getAction()) || ("transferTo".equals(l.getAction()))) {
					System.out.println(l.toString());
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR, could not show log from showWithdrawMoneyLog in LogTest");
			e.printStackTrace();
		}

	}

	/**
	 * Remove all remaining persons from the database
	 * 
	 * @throws Exception
	 */
	public void remove() throws Exception {
		try {
			List<Log> list = logs.list();
			for (Log l : list) {
				if (l.getId() > 0) {
					logs.remove(l);
					System.out.println("Deleting ... : " + l.toString());
				} else {
					System.out.println("Delete failed ... : " + l.toString());
				}
			}
		} finally {
		}
	}

	public void listAllTransactionsInSingleAccount() {

		try {
			List<Account> list = accounts.list();

			int accountId = list.get(0).getId();
			List<Log> logList = logs.showLog(accountId);

			for (Log l : logList) {
				System.out.println(l.toString());
			}
		} catch (Exception e) {
			System.out.println("ERROR, could not show log from listAllTransactionsInSingleAccount in LogTest");
			e.printStackTrace();
		}

	}

	// public void showAccountBalance() {
	//
	// List<Account> list;
	//
	// try {
	// list = accounts.list();
	// int accountId = list.get(0).getId();
	//
	// SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	//
	//
	//
	// //date from
	// LocalDate dateF = LocalDate.of( 2017,01,01);
	// LocalDate dateT = LocalDate.of( 2020,01,01);
	//
	// Date dateFrom =
	// Date.from(dateF.atStartOfDay(ZoneId.systemDefault()).toInstant());
	// Date dateTo =
	// Date.from(dateT.atStartOfDay(ZoneId.systemDefault()).toInstant());
	//
	// List<Log> listOfLogs = logs.showLog(accountId);
	//
	// System.out.println("Showing account balance from " + dateF + " to " +
	// dateT + ":");
	// for (Log l : listOfLogs) {
	// if(l.getDate().after(dateFrom) && l.getDate().before(dateTo)) {
	// System.out.println(l.getAccountBalance());
	// }
	// }
	//
	// } catch (Exception e) {
	// System.out.println("ERROR in showAccountBalance, could not get balance
	// list of account");
	// e.printStackTrace();
	// }
	//
	// }

}
