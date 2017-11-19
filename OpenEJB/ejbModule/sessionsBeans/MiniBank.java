package sessionsBeans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import entity.Account;

/**
 * Session Bean implementation class MiniBank
 */
@Stateless
@LocalBean
public class MiniBank implements MiniBankRemote, MiniBankLocal {

    /**
     * Default constructor. 
     */
    public MiniBank() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void deposit(Account acc, int amount) {
		acc.setBalance(acc.getBalance() + amount);
	}

	@Override
	public String withdraw(Account acc, int amount) {
		// TODO Auto-generated method stub
		return null;
	}


}
