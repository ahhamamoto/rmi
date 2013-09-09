import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

public class WebAdmin {
	private ArrayList<String> servers;
	
	/**
	 * Construtor que inicializa a lista de servidores.
	 */
	public WebAdmin() {
		this.servers = new ArrayList<String>();
	}
	
	/**
	 * Método para adicionar um servidor na lista do WebAdmin.
	 * @param ip String contendo o IP do servidor.
	 */
	public void addServer(String ip) {
		Iterator<String> it = servers.iterator();
		boolean found = false;
		while (it.hasNext() && !found) {
			if (it.equals(ip)) found = true;
		}
		if (!found) servers.add(ip);
	}
	
	/**
	 * Método que migra a board de um servidor para outro.
	 * @param server1 Servidor que a board pertence.
	 * @param server2 Servidor destino da board.
	 * @param board Nome da board a ser transferida.
	 */
	public void migrate(String server1, String server2, String board) throws RemoteException {
		IServer s = new RmiServer();
		try {
			s = (IServer)Naming.lookup("//" + server1 + "/RmiServer");
			s.changeBoard(server2, board);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		
	}
}