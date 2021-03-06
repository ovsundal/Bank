package sessionBeans;

import entity.*;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;

/**
 * Session Bean implementation class Personer
 */
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
@Stateful(name = "Persons")
public class Persons implements PersonsRemote, PersonsLocal {
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public Persons() {
	}

	/**
	 * Add a new person to the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Person person) {
		entityManager.persist(person);
		entityManager.flush();
	}

	/**
	 * Remove a person from the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Person person) {
		entityManager.remove(entityManager.merge(person));
		entityManager.flush();
	}

	/**
	 * Lists all persons in the database. Only used for testing
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Person> list() {
		Query query = entityManager.createQuery("SELECT p from Person as p");
		List<Person> l = query.getResultList();
		return l;
	}
	/**
	 * Find person based on personId
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Person get(String personId) {

		String pnummer = personId;
		Query query = entityManager.createQuery("SELECT p from Person as p WHERE p.personId LIKE :personnummer")
				.setParameter("personnummer", personId);
		if (query.getSingleResult() == null) {
			throw new IllegalArgumentException("Could not find any person for personId " + personId);
		}
		Person p = (Person) query.getSingleResult();
		return p;
	}
}