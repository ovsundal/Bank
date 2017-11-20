package sessionsBeans;

import javax.ejb.Local;

import entity.Account;

@Local
public interface MiniBankLocal {

	public String deposit(int accountId, int amount);
	public String withdraw(int accountId, int amount);
	public String getAccountBalance(int accountId);
	public String transfer(int fromAccId, int toAccId, int amount);
}
