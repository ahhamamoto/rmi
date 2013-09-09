import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Iterator;

public class RmiServer extends UnicastRemoteObject implements IServer {
	private ArrayList<Board> boards; /**< Lista de boards no servidor. */
	
	/**
	 * Construtor do servidor, inicializa a lista de clientes e inicializa
	 * a última coordenada (x, y) daquele servidor. 
	 */
	public RmiServer() throws RemoteException {
		this.boards = new ArrayList<Board>();
	}
	
	/**
	 * Método que envia a linha desenhada por um dos clientes para o servidor
	 * que faz a atualização na board.
	 * @param x1 Coordenada x do ponto 1.
	 * @@aram y1 Coordenada y do ponto 1.
	 * @param x2 Coordenada x do ponto 2.
	 * @param y2 Coordenada y do ponto 2.
	 * @param board Parâmetro com o nome da board que está 
	 * enviando a linha.
	 */
	public void sendLine(int x1, int y1, int x2, int y2, String board) throws RemoteException {
		Iterator<Board> it = boards.iterator();
		boolean found = false;
		while (it.hasNext() && !found) {
			Board b = it.next();
			if (b.getName().equals(board)) {
				b.update(x1, y1, x2, y2);
				found = true;
			}
		}
	}

	/**
	 * Método que coloca o cliente em uma board ou cria uma board
	 * com o nome requisitado.
	 * @param client Cliente que está querendo entrar ou criar uma board.
	 * @oaram board Nome da board que o cliente quer entrar ou criar.
	 */
	public void setClient(IClient client, String board) throws RemoteException {
		Iterator<Board> it = boards.iterator();
		boolean found = false;
		while (it.hasNext() && !found) {
			Board b = it.next();
			if (b.getName().equals(board)) {
				b.addClient(client);
				found = true;
			}
		}
		if (!found) {
			Board new_board = new Board(board);
			new_board.addClient(client);
			boards.add(new_board);
		}
	}
	
	/**
	 * Método que muda o board de um servidor para outro.
	 * @param server IP do servidor de destino da board.
	 * @param board O nome da board a ser transferida.
	 */
	public void changeBoard(String server, String board) throws RemoteException {
		IServer s = new RmiServer();
		try {
			s = (IServer)Naming.lookup("//" + server + "/RmiServer");
			Iterator<Board> it = boards.iterator();
			boolean found = false;
			Board b;
			while (it.hasNext() && !found) {
				b = it.next();
				if (b.getName().equals(board)) {
					found = true;
					 s.setBoard(server, b);
					 boolean removed = boards.remove(b);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para redefinir os IP's do servidor dos clientes.
	 * @param board Nome da nova board.
	 */
	public void setBoard(String server, Board board) throws RemoteException {
		boards.add(board);
		board.setNewServer(server);
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