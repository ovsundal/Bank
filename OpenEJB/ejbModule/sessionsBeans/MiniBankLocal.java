package sessionsBeans;

import javax.ejb.Local;

import entity.Account;

@Local
public interface MiniBankLocal {

	public void deposit(int accountId, int amount);
	public String withdraw(Account acc, int amount);
}
