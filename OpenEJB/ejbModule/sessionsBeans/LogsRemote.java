package sessionsBeans;

import javax.ejb.Remote;

import entity.Log;

@Remote
public interface LogsRemote {

	public void add(Log l);
}
