package sessionsBeans;

import javax.ejb.Remote;
import entity.Person;

@Remote
public interface KundeImportRemote {
	public void newKunde(Person p) throws Exception;
}
