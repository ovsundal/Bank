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

import entity.Konto;
import entity.Person;

/**
 * Session Bean implementation class Kontoer
 */
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@TransactionAttribute(value= TransactionAttributeType.SUPPORTS)
@Stateless(name= "Kontoer")
@LocalBean
public class Kontoer implements KontoerRemote, KontoerLocal {
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	
	private EntityManager entityManager;
	
	@EJB(name="Personer", beanInterface=PersonerLocal.class)
	PersonerLocal personerBean;

    /**
     * Default constructor. 
     */
    public Kontoer() {
        // TODO Auto-generated constructor stub
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Konto k) throws Exception {
    	entityManager.persist(k);
		
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Konto k) throws Exception {
    	entityManager.remove(entityManager.merge(k));
		
	}
    
    @SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Konto> list() throws Exception {
    	//bruker Java Persistence Query Language, ikke SQL
    			Query query = entityManager.createQuery("SELECT k from Konto as k");
    			List <Konto> l = query.getResultList();
    			System.out.println("Konto fra db: " + l);
    			return l;
	}
    
    @TransactionAttribute(value= TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void createAccountAndPerson(String navn, String saldo, Date dato, Person p) {
		// TODO Auto-generated method stub
    	System.out.println("Lager ny konto...");
		Konto konto = new Konto(navn, saldo, dato, p);
		entityManager.persist(konto);
	}

}
