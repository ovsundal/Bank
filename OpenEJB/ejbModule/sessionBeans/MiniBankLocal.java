package sessionBeans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entity.Account;
import entity.Log;
import entity.Person;

/**
 * Minibank contains all methods a minibank-client needs. This class does
 * lookups and db inserts on behalf of the client and ties together all the other beans. 
 * All methods here are defined in the other beans
 * @author Ove
 *
 */
@Local
public interface MiniBankLocal {

	public String deposit(int accountId, int amount);

	public String withdraw(int accountId, int amount);

	public String getAccountBalance(int accountId);

	public String transfer(int fromAccId, int toAccId, int amount);

	public String createAccount(String personId, String accountName, int initialBalance, Calendar dateCreated);

	public boolean validateCardNumber(String cardNumber);

	public boolean validatePin(String cardNumber, String pin);

	public int getAccountId(String cardNumber);

	public Person getAccountOwner(int accountId);

	public List<Account> getAccounts(int personId);

	/**
	 * Returns all logs for an account
	 * 
	 * @param accountId
	 * @return
	 */
	public List<Log> showLog(int accountId);
	
	/**
	 * only used for generating test data with older log date
	 * @param accountId
	 * @param amount
	 * @param date
	 * @return
	 */
	public String deposit(int accountId, int amount, Calendar date);

	/**
	 * only used for generating test data with older log date
	 * @param accountId
	 * @param amount
	 * @param date
	 * @return
	 */
	public String withdraw(int accountId, int amount, Calendar date);
}
