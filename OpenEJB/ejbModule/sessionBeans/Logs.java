package sessionBeans;

import java.util.List;

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

import entity.Log;

/**
 * Session Bean implementation class Logs
 */
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
@Stateless(name = "Logs")
@LocalBean
public class Logs implements LogsRemote, LogsLocal {
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public Logs() {
	}

	/**
	 * Adds a log to db
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Log l) {
		entityManager.merge(l);
		entityManager.flush();
	}

	/**
	 * Lists all logs belonging to an account
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Log> showLog(int accountId) {

		try {
			Query query = entityManager
					.createQuery("SELECT l from Log AS l WHERE l.thisAccountId LIKE :id OR l.otherAccountId LIKE :id")
					.setParameter("id", accountId);
			List<Log> l = query.getResultList();
			return l;
		} catch (Exception e) {
			System.out.println("ERROR, could not access database in showLog() ");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Lists all logs in db. Only used for testing
	 */
	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Log> list() throws Exception {
		Query query = entityManager.createQuery("SELECT l from Log as l");
		List<Log> l = query.getResultList();
		return l;
	}

	/**
	 * Removes a log from db. Only used for testing
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Log l) throws Exception {
		entityManager.remove(entityManager.merge(l));
		entityManager.flush();
	}
}
