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
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
@Stateless(name = "Accounts")
@LocalBean
public class Accounts implements AccountsRemote, AccountsLocal {

	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@EJB(name = "Personer", beanInterface = PersonsLocal.class)
	PersonsLocal personerBean;

	public Accounts() {
	}

	/**
	 * Create an account and assign it to a Person owner
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Account k) throws Exception {

		entityManager.persist(k);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Account k) throws Exception {
		entityManager.remove(entityManager.merge(k));
		entityManager.flush();

	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Account> list() throws Exception {
		// bruker Java Persistence Query Language, ikke SQL
		Query query = entityManager.createQuery("SELECT k from Account as k");
		List<Account> l = query.getResultList();
		return l;
	}

	/**
	 * Method for viewing current balance
	 */
	// todo: necessary to send whole account?
	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getAccountBalance(int accountId) throws Exception {
		try {
			Query query = entityManager.createQuery("SELECT a.balance from Account as a WHERE a.id LIKE :id")
					.setParameter("id", accountId);
			int balance = (int) query.getSingleResult();
			return balance;
		} catch (Exception e) {
			System.out.println("ERROR, could not access database: ");
			e.printStackTrace();
		}
		// todo: er det akseptabelt � returnere -1 hvis ikke kontotilgang? Kan
		// en konto v�re negativ?
		return -1;
	}

	@Override
	public Account get(int accountId) throws Exception {
		try {
			Query query = entityManager.createQuery("SELECT a from Account as a WHERE a.id LIKE :id")
					.setParameter("id", accountId);
			Account acc = (Account) query.getSingleResult();
			System.out.println("Returning this account: " + acc);
			return acc;
		} catch (Exception e) {
			System.out.println("ERROR, could not access database and retrieve account: ");
			e.printStackTrace();
		}
		return null;
	}
}
