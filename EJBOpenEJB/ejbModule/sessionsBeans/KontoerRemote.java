package sessionsBeans;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.Konto;
import entity.Person;

@Remote
public interface KontoerRemote {
	public void add(Konto k) throws Exception;
	public void remove(Konto k) throws Exception;
	public List <Konto> list() throws Exception;
}
