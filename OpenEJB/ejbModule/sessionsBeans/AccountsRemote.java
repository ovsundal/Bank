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
}
