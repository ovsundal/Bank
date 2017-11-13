package sessionsBeans;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import entity.Person;

/**
 * Session Bean implementation class KundeImport
 */
@Stateless
@LocalBean
@EJB(name="Personer", beanInterface = PersonerLocal.class)
public class KundeImport implements KundeImportRemote {
	@PersistenceContext (unitName = "bankdb-unit")
	private EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public KundeImport() {
        // TODO Auto-generated constructor stub
    }
    
    public void newKunde(Person p) throws Exception {
    	Context ctx = new InitialContext();
    	PersonerLocal personerSessionBean = (PersonerLocal)ctx.lookup("java:comp/env/Personer");
    	personerSessionBean.add(p);
    }


}
