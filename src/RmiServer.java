import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Iterator;

public class RmiServer extends UnicastRemoteObject implements IServer {
	private ArrayList<IClient> clients; /**< Lista de clientes no servidor. */
	private int last_x; /**< A última coordenada no eixo x. */
	private int last_y; /**< A última coordenada no eixo y. */

	/**
	 * Construtor do servidor, inicializa a lista de clientes e inicializa
	 * a última coordenada (x, y) daquele servidor. 
	 */
	public RmiServer() throws RemoteException {
		clients = new ArrayList<IClient>();
		last_x = 2;
		last_y = 25;
	}
	
	public void sendLine(int x1, int y1, int x2, int y2) throws RemoteException {
		System.out.println("New coordinates received: " + x1 + " " + y1 + " " + x2 + " " + y2);
		Iterator<IClient> it = clients.iterator();
		while(it.hasNext()) {
			IClient c = it.next();
			c.drawLine(x1, y1, x2, y2);
		}
		last_x = x2;
		last_y = y2;
	}

	public int[] setClient(IClient client) throws RemoteException {
		clients.add(client);
		Iterator<IClient> it = clients.iterator();
		int[] coord = {last_x, last_y};
		return coord;
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