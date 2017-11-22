package sessionsBeans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import entity.Log;

/**
 * Session Bean implementation class Logs
 */
@Stateless
@LocalBean
public class Logs implements LogsRemote, LogsLocal {
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public Logs() {}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Log l) {
		entityManager.merge(l);
		entityManager.flush();
	}

}
