package sessionBeans;

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
import entity.Log;
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
	@EJB(name = "Logs", beanInterface = LogsLocal.class)
	LogsLocal logsBean;

	public Accounts() {
	}

	/**
	 * Create an account and assign it to a Person owner
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Account k) {

		entityManager.persist(k);
		entityManager.flush();

		// log create account event
		try {
			Log l = new Log(k.getDateCreated(), "create", 0, k.getBalance(), k.getId());
			logsBean.add(l);
		} catch (Exception e) {
			System.out.println("Error, could not log add account event");
		}
	}
	/**
	 * Returns balance of an account
	 * 
	 * @throws Exception
	 */
	@Override
	public int getAccountBalance(int accountId) {

		Query query = entityManager.createQuery("SELECT a.balance from Account as a WHERE a.id LIKE :id")
				.setParameter("id", accountId);
		if (query == null) {
			throw new IllegalArgumentException("Could not find any account balance for accountId " + accountId);
		}
		int balance = (int) query.getSingleResult();
		return balance;
	}

	/**
	 * Returns an account
	 */
	@Override
	public Account get(int accountId) {

		Query query = entityManager.createQuery("SELECT a from Account as a WHERE a.id LIKE :id").setParameter("id",
				accountId);
		if (query.getSingleResult() == null) {
			throw new IllegalArgumentException("Could not find any account for accountId " + accountId);
		}
		Account acc = (Account) query.getSingleResult();
		return acc;
	}

	/**
	 * Returns all accounts owned by person
	 */
	@Override
	public List<Account> getOwnedAccounts(int personId) {

		Query query = entityManager.createQuery("SELECT a from Account as a WHERE a.owner.id LIKE :id")
				.setParameter("id", personId);
		if (query.getResultList() == null) {
			throw new IllegalArgumentException("Could not find any accounts belonging to person with id " + personId);
		}
		@SuppressWarnings("unchecked")
		List<Account> acc = query.getResultList();
		return (List<Account>) acc;
	}

	/**
	 * Lists all accounts in db. Only used for testing
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Account> list() throws Exception {
		// bruker Java Persistence Query Language, ikke SQL
		Query query = entityManager.createQuery("SELECT k from Account as k");
		List<Account> l = query.getResultList();
		return l;
	}
	/**
	 * Removes an account. Only used for testing
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remove(Account k) {
		entityManager.remove(entityManager.merge(k));
		entityManager.flush();
	}
}
