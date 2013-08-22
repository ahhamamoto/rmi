import java.rmi.Remote;
import java.rmi.RemoteException;

/**
	* Classe que tem somente a interface que o servidor implementa do RMI.
	*/
public interface RmiServerIntf extends Remote {
	public String getMessage() throws RemoteException;
  public int[] getLastCoordinate() throws RemoteException;
  public void sendCoordinate(int x, int y) throws RemoteException;
}