package sessionsBeans;

import java.util.Date;

import javax.ejb.Remote;

import entity.Account;
import entity.Person;

@Remote
public interface MiniBankRemote {

	public String deposit(int accountId, int amount);
	public String withdraw(int accountId, int amount);
	public String getAccountBalance(int accountId);
	public String transfer(int fromAccId, int toAccId, int amount);
	public String createAccount(String personId, String accountName, int initialBalance, Date dateCreated);
	public boolean validateCardNumber(String cardNumber);
}