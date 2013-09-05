/**
 * Classe que tem somente a interface do servidor implementa do RMI.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServerIntf extends Remote {
	public String getMessage() throws RemoteException;
}