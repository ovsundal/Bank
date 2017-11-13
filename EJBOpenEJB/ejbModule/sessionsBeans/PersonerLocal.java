package sessionsBeans;

import entity.Person;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PersonerLocal {
	public void add(Person p) throws Exception;

	public void remove(Person p) throws Exception;

	public List<Person> list() throws Exception;
}