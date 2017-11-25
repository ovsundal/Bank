package sessionBeans;

import java.util.List;

import javax.ejb.Remote;

import entity.Account;

@Remote
public interface AccountsRemote {
	/**
	 * Creates a new account
	 * @param k
	 */
	public void add(Account k);
	/**
	 * Returns account balance
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public int getAccountBalance(int accountId);
	/**
	 * Returns entire account object belonging to accountId
	 * @param accountId
	 * @return
	 */
	public Account get(int accountId);
	/**
	 * Returns all accounts belonging to person
	 * @param personId
	 * @return
	 */
	List<Account> getOwnedAccounts(int personId);
	/**
	 * Lists all accounts in database. Only used for testing
	 * @return
	 * @throws Exception
	 */
	public List<Account> list() throws Exception;
	/**
	 * Removes an account. Only used for testing
	 * @param k
	 * @throws Exception
	 */
	public void remove(Account k);
}
