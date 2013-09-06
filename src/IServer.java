import java.rmi.*;

public interface IServer extends Remote{
	public void setClient(IClient client)	throws RemoteException;

	public void sendLine(int x1, int y1, int x2, int y2, IClient sender) throws RemoteException;
}