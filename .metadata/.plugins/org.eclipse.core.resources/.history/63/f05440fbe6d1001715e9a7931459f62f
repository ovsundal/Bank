package sessionBeans;

import entity.Person;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PersonsLocal {
	
	public void add(Person p) throws Exception;
	public Person get(String personnummer) throws Exception;
	public void remove(Person p) throws Exception;
	public List<Person> list() throws Exception;
}