package sessionsBeans;

import java.util.List;

import javax.ejb.Local;

import entity.Log;
import entity.Person;

@Local
public interface LogsLocal {

	public void add(Log l);
	public List<Log> list() throws Exception;
}
