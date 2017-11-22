package sessionsBeans;

import javax.ejb.Local;

import entity.Log;

@Local
public interface LogsLocal {

	public void add(Log l);
}
