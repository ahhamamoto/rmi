import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

public class Board {
	private ArrayList<IClient> clients; /**< Lista de clientes no servidor. */
	private int last_x; /**< A última coordenada no eixo x. */
	private int last_y; /**< A última coordenada no eixo y. */
	private String name; /**< Nome da board. */
	
	/**
	 * Construtor que define o nome da board.
	 * @param name Parâmetro contendo o nome da board.
	 */
	public Board(String name) {
		this.name = name;
		this.clients = new ArrayList<IClient>();
	}
	
	/**
	 * Método que retorna o nome da board.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Método que adiciona um cliente na lista de clientes da board.
	 */
	public void addClient(IClient client) {
		this.clients.add(client);
	} 
	
	public void setNewServer(String new_server) throws RemoteException {
		Iterator<IClient> it = clients.iterator();
		try {
			while(it.hasNext()) {
				IClient c = it.next();
				c.changeServer(new_server);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Método que faz a atualização das linhas nos clientes.
	 * @param x1 Coordenada do ponto 1 no eixo x.
	 * @param y1 Coordenada do ponto 1 no eixo y.
	 * @param x2 Coordenada do ponto 2 no eixo x.
	 * @oaram y2 Coordenada do ponto 2 no eixo y.
	 */
	public void update(int x1, int y1, int x2, int y2) throws RemoteException {
		Iterator<IClient> it = clients.iterator();
		while(it.hasNext()) {
			IClient c = it.next();
			c.drawLine(x1, y1, x2, y2);
		}
		this.last_x = x2;
		this.last_y = y2;
	}
}