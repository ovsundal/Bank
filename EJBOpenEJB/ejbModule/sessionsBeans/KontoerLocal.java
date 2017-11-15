package sessionsBeans;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entity.Konto;
import entity.Person;

@Local
public interface KontoerLocal {
	
	public void createAccount(String navn, String saldo, Date dato, Person p);
	public void add(Konto k) throws Exception;
	public void remove(Konto k) throws Exception;
	public List <Konto> list() throws Exception;
}