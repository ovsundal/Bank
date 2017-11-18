package sessionsBeans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import entity.Card;
import entity.Person;

/**
 * Session Bean implementation class Cards
 */
@Stateless
@LocalBean
public class Cards implements CardsRemote, CardsLocal {
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public Cards() {}

    /**
	 * Add a card to the account
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Card card) throws Exception {
		entityManager.persist(card);
		entityManager.flush();
	}
}
