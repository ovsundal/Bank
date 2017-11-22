package sessionsBeans;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entity.Log;
import entity.Person;

@Local
public interface LogsLocal {

	public void add(Log l);
	public List<Log> list() throws Exception;
	public void remove(Log l) throws Exception;
	public List<Log> showLog(int accountId);
//	public List<Log> getAccountBalance(int accountId, Date fromDate, Date toDate);
}
