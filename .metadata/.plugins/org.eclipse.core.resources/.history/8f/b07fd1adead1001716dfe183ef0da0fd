package sessionBeans;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.Log;
import entity.Person;

@Remote
public interface LogsRemote {

	public void add(Log l);
	public List<Log> list() throws Exception;
	public void remove(Log l) throws Exception;
	public List<Log> showLog(int accountId);
}
