package sessionsBeans;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import entity.Account;

/**
 * Session Bean implementation class MiniBank
 */
@Stateless
@LocalBean
public class MiniBank implements MiniBankRemote, MiniBankLocal {
	
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	@EJB(name = "Accounts", beanInterface = AccountsLocal.class)
	AccountsLocal accountsBean;
    /**
     * Default constructor. 
     */
    public MiniBank() {}

    /**
     * Used to deposit money into an account
     */
	@Override
	public String deposit(int accountId, int amount) {
		
		//check that amount to withdraw is not negative
		if(amount <= 0) {
			return "You cannot deposit a negative amount";
		}
		
		Account acc;
		try {
			acc = accountsBean.get(accountId);
			acc.setBalance(acc.getBalance() + amount);
			entityManager.flush();
			acc = accountsBean.get(accountId);
			
			return "You have deposited " + amount + " into account " + acc.getName();
		} catch (Exception e) {
			System.out.println("ERROR, could not get account from database. Money was not deposited");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Used to withdraw money from an account
	 */
	@Override
	public String withdraw(int accountId, int amount) {
		
		//check that amount to withdraw is not negative
		if(amount <= 0) {
			return "You cannot withdraw a negative amount";
		}
		
		try {
			Account acc;
			acc = accountsBean.get(accountId);
			
			//check that there is enough money on the account
			if(acc.getBalance() < amount) {
				return "There is not enough money on the account to perform the transaction";
			}
			
			acc.setBalance(acc.getBalance() - amount);
			return "You have withdrawn " + amount + " from account " + acc.getName();
		} catch(Exception e) {
			System.out.println("Error, could not connect to database, or something else is wrong");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Used to get account balance
	 */
	@Override
	public String getAccountBalance(int accountId) {
		try {
			
			int balance = accountsBean.getAccountBalance(accountId);
			return "Balance: " + String.valueOf(balance);
		} catch (Exception e) {
			System.out.println("ERROR, could not connect to database, or something else is wrong");
		}
		return "";
	}
	
	@Override
	@TransactionAttribute(value= TransactionAttributeType.REQUIRES_NEW)
	public String transfer(int fromAccId, int toAccId, int amount) {
		
		String response = "Error, cannot transfer a negative amount";
		
		if(amount <= 0) {
			return response;
		}
		
		try {
			Account fromAcc = accountsBean.get(fromAccId);
			
			//abort if account does not have enough funds 
			if(fromAcc.getBalance() < amount) {
				response = "Insufficient funds for transfer. Aborted";
				return response;
			}
			
			Account toAcc = accountsBean.get(toAccId);
			
			fromAcc.setBalance(fromAcc.getBalance() - amount);
			//add response to log here
			toAcc.setBalance(toAcc.getBalance() + amount);
			//add response to log here
			response = "Transferred " + amount + " from account: " + fromAcc.getName() + " to account " + toAcc.getName();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}


}
