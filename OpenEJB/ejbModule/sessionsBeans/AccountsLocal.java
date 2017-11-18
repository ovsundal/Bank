package sessionsBeans;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entity.Account;
import entity.Person;

@Local
public interface AccountsLocal {

	/**
	 * Create an account
	 * @param k
	 * @throws Exception
	 */
	public void add(Account k) throws Exception;
	public void remove(Account k) throws Exception;
	public List <Account> list() throws Exception;
	public int getAccountBalance(int accountId) throws Exception;
}
