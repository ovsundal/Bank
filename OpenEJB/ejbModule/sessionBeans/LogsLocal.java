package sessionBeans;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entity.Log;
import entity.Person;

@Local
public interface LogsLocal {
	/**
	 * Adds a log to db
	 * 
	 * @param l
	 */
	public void add(Log l);

	/**
	 * Lists all logs belonging to an account
	 * 
	 * @param accountId
	 * @return
	 */
	public List<Log> showLog(int accountId);

	/**
	 * Lists all logs in db. Only used for testing
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Log> list() throws Exception;

	/**
	 * Removes a log from db. Only used for testing
	 * 
	 * @param l
	 * @throws Exception
	 */
	public void remove(Log l) throws Exception;
}
