package sessionsBeans;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.Account;
import entity.Person;

@Remote
public interface AccountsRemote {
	public void add(Account k) throws Exception;
	public void remove(Account k) throws Exception;
	public List <Account> list() throws Exception;
	public int getAccountBalance(int accountId) throws Exception;
	public Account get(int accountId) throws Exception;
	List<Account> acquireFromPerson(int personId) throws Exception;
}
