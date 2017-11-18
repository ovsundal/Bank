package sessionsBeans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import entity.Account;
import entity.Person;

/**
 * Session Bean implementation class Accounts
 */
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@TransactionAttribute(value= TransactionAttributeType.SUPPORTS)
@Stateless(name= "Accounts")
@LocalBean
public class Accounts implements AccountsRemote, AccountsLocal {
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	
	private EntityManager entityManager;
	
	@EJB(name="Personer", beanInterface=PersonsLocal.class)
	PersonsLocal personerBean;

    public Accounts() {}
    
    /**
     * Create an account and assign it to a Person owner
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Account k) throws Exception {

    	entityManager.persist(k);
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Account k) throws Exception {
    	entityManager.remove(entityManager.merge(k));
		
	}
    
    @SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Account> list() throws Exception {
    	//bruker Java Persistence Query Language, ikke SQL
    			Query query = entityManager.createQuery("SELECT k from Account as k");
    			List <Account> l = query.getResultList();
    			return l;
	}

    /**
     * Method for viewing current balance
     */
	@Override
	public int getBalance(Account k) throws Exception {
		//continue here
		return 0;
	}
}
