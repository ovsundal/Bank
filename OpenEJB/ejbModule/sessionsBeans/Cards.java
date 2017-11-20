package sessionsBeans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

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
		//bruke merge her?
		entityManager.persist(card);
		entityManager.flush();
	}

	/**
	 * Lists all cards in the database. Only used for testing
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List <Card> list() throws Exception {
		//bruker Java Persistence Query Language, ikke SQL
		Query query = entityManager.createQuery("SELECT c from Card as c");
		List <Card> l = query.getResultList();
		return l;
	}

	@Override
	public void remove(Card c) {
		entityManager.remove(entityManager.merge(c));
		entityManager.flush();
	}
}