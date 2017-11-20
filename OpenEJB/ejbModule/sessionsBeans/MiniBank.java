package sessionsBeans;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

	@Override
	public void deposit(int accountId, int amount) {
		
		Account acc;
		try {
			acc = accountsBean.get(accountId);
			acc.setBalance(acc.getBalance() + amount);
			entityManager.flush();
			acc = accountsBean.get(accountId);
			
			System.out.println("You have deposited " + amount + " into account " + acc.getName());
		} catch (Exception e) {
			System.out.println("ERROR, could not get account from database. Money was not deposited");
			e.printStackTrace();
		}
	}

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


}
