package sessionsBeans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	public Cards() {
	}

	/**
	 * Add a card to the account
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Card card) throws Exception {
		entityManager.persist(card);
		entityManager.flush();
	}

	/**
	 * Lists all cards in the database. Only used for testing
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Card> list() throws Exception {
		// bruker Java Persistence Query Language, ikke SQL
		Query query = entityManager.createQuery("SELECT c from Card as c");
		List<Card> l = query.getResultList();
		return l;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Card c) {
		entityManager.remove(entityManager.merge(c));
		entityManager.flush();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean validateCardNumber(String cardNumber) {

		boolean isValid = false;
		try {
			Query query = entityManager.createQuery("SELECT c from Card as c WHERE c.cardNumber LIKE :cardNumber")
					.setParameter("cardNumber", cardNumber);

			if (query.getSingleResult() != null) {
				isValid = true;
				return isValid;
			}
		} catch (NoResultException ex) {
			System.out.println("Could not find any results with this card number");
		} catch (Exception e) {
			System.out.println("Error, something went wrong");
			e.printStackTrace();
		}
		return isValid;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean validatePin(String cardNumber, String pin) {
		
		boolean isValid = false;
		try {
			Query query = entityManager.createQuery("SELECT c from Card as c WHERE c.cardNumber LIKE :cardNumber AND c.pin LIKE :pin")
					.setParameter("cardNumber", cardNumber)
					.setParameter("pin", pin);

			if (query.getSingleResult() != null) {
				isValid = true;
				return isValid;
			}
		} catch (NoResultException ex) {
			System.out.println("Could not find any results with this combination of card number and pin");
		} catch (Exception e) {
			System.out.println("Error, something went wrong");
			e.printStackTrace();
		}
		return isValid;
	}

}
