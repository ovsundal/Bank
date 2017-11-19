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
		} catch (Exception e) {
			System.out.println("ERROR, could not get account from database. Money was not deposited");
			e.printStackTrace();
		}
	}

	@Override
	public String withdraw(Account acc, int amount) {
		// TODO Auto-generated method stub
		return null;
	}


}
