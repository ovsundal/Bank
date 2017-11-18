package sessionsBeans;
import entity.*;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;

/**
 * Session Bean implementation class Personer
 */
@Stateful(name= "Persons")
public class Persons implements PersonsRemote, PersonsLocal {
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	public Persons() {}
	
	/**
	 * Add a new person to the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Person person) throws Exception {
		entityManager.persist(person);
		}
	/**
	 * Remove a person from the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Person person) throws Exception {
		entityManager.remove(entityManager.merge(person));
		}
	/**
	 * Lists all persons in the database. Only used for testing
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List <Person> list() throws Exception {
		//bruker Java Persistence Query Language, ikke SQL
		Query query = entityManager.createQuery("SELECT p from Person as p");
		List <Person> l = query.getResultList();
		return l;
	}
	/**
	 * Method for querying a person based on his/hers personnummer
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Person find(String personnummer) {
		
		Person p = null;
		
		try {
			String pnummer = personnummer;
			//todo: protect against SQL injection
			Query query = entityManager.createQuery("SELECT p from Person as p WHERE p.personId LIKE :personnummer")
					.setParameter("personnummer", personnummer);
			p = (Person) query.getSingleResult();
			return p;
		} catch(NoResultException ex) {
			System.out.println("Could not find any results with this personnummer");
		} catch(Exception e) {
			System.out.println("Error, something went wrong");
			e.printStackTrace();
		}
		return p;
	}
}