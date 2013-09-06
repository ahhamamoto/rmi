import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Iterator;

public class RmiServer extends UnicastRemoteObject implements IServer {
	ArrayList <IClient> clients;

	public RmiServer() throws RemoteException {
    	clients = new ArrayList<IClient>();
    }
	
	public void sendLine(int x1, int y1, int x2, int y2, IClient sender) throws RemoteException{
		System.out.println("New coordinates received: " + x1 +" " + y1+" "+ x2 +" " + y2);
		Iterator<IClient> it = clients.iterator();
		while(it.hasNext()){
			IClient c = it.next();
			if(c!=sender)
				c.drawLine(x1,y1,x2,y2);
		}
	}

	public void setClient(IClient client)	throws RemoteException{
		clients.add(client);
	}
	
	public static void main(String args[]) throws Exception {
		System.out.println("RMI Server started.");
		
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry started");
		} catch (RemoteException e) {
			System.out.println("RMI registry already exists");
		}
		
		RmiServer obj = new RmiServer();
		
		Naming.rebind("//localhost/RmiServer", obj);
		System.out.println("PeerServer bound in registry");
	}
}