import java.rmi.*;

public interface IClient extends Remote{
	public void drawLine(int x1, int y1, int x2, int y2) throws RemoteException;
}