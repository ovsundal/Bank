package sessionBeans;

import entity.Person;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PersonsLocal {
	/**
	 * Adds a person to db
	 * 
	 * @param p
	 * @throws Exception
	 */
	public void add(Person p) throws Exception;

	/**
	 * Get person-object from personId
	 * 
	 * @param personnummer
	 * @return
	 * @throws Exception
	 */
	public Person get(String personId);

	/**
	 * Removes a person from db. Only used for testing
	 * 
	 * @param p
	 * @throws Exception
	 */
	public void remove(Person p);

	/**
	 * Lists all persons in db. Only used for testing
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Person> list();
}