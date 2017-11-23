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

	public boolean validatePin(String cardNumber, String pin);

	// only used for generating test data with older log date
	public String deposit(int accountId, int amount, Date date);

	// only used for generating test data with older log date
	public String withdraw(int accountId, int amount, Date date);

	// only used for generating test data with older log date
	public String transfer(int fromAccId, int toAccId, int amount, Date date);

	public int getAccountId(String cardNumber);
}