package sessionsBeans;

import javax.ejb.Local;

import entity.Account;

@Local
public interface MiniBankLocal {

	public String deposit(int accountId, int amount);
	public String withdraw(int accountId, int amount);
}
