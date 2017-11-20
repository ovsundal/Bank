package sessionsBeans;

import javax.ejb.Remote;

import entity.Account;

@Remote
public interface MiniBankRemote {

	public void deposit(int accountId, int amount);
	public String withdraw(int accountId, int amount);
}
