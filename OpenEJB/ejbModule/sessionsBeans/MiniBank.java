package sessionsBeans;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import entity.Account;

/**
 * Session Bean implementation class MiniBank
 */
@Stateless
@LocalBean
public class MiniBank implements MiniBankRemote, MiniBankLocal {
	
	@EJB(name = "Accounts", beanInterface = AccountsLocal.class)
	AccountsLocal accountsBean;
    /**
     * Default constructor. 
     */
    public MiniBank() {}

	@Override
	public void deposit(Account acc, int amount) {
	
		
		System.out.println("current balance of account" + acc.getBalance() + " - Adding " + amount + " to the account");
		acc.setBalance(acc.getBalance() + amount);
		System.out.println("Deposit complete. Balance is now: " + acc);
	}

	@Override
	public String withdraw(Account acc, int amount) {
		// TODO Auto-generated method stub
		return null;
	}


}
