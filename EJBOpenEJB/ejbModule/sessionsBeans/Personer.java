package sessionsBeans;
import entity.*;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;

/**
 * Session Bean implementation class Personer
 */
@Stateful(name= "Personer")
public class Personer implements PersonerRemote, PersonerLocal {
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	/*** Default constructor.*/
	public Personer() {
		// TODO: Auto-generated constructor stub
		}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Person person) throws Exception {
		entityManager.persist(person);
		}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Person person) throws Exception {
		entityManager.remove(entityManager.merge(person));
		}
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List <Person> list() throws Exception {
		//bruker Java Persistence Query Language, ikke SQL
		Query query = entityManager.createQuery("SELECT p from Person as p");
		List <Person> l = query.getResultList();
		return l;
	}
}