package sessionsBeans;

import javax.ejb.Remote;

import entity.Account;

@Remote
public interface MiniBankRemote {

	public String deposit(int accountId, int amount);
	public String withdraw(int accountId, int amount);
	public String getAccountBalance(int accountId);
	public String transfer(int fromAccId, int toAccId, int amount);
}
