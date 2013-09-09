import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;  

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
			it.next();
			if (it.equals(ip)) found = true;
		}
		if (!found) servers.add(ip);
	}

	/**
	 * Método para listar servidores adicionados no WebAdmin.
	 */
	public void list() {
		Iterator<String> it = servers.iterator();
		System.out.println("Servidores:");
		while (it.hasNext()) {
			String server = it.next();
			System.out.println(server);
		}
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
		int option = 0;
		Scanner in = new Scanner(System.in);
		String server1, server2, board;

		WebAdmin admin = new WebAdmin(); 

		do{
			System.out.println("0 - Sair");
			System.out.println("1 - Adicionar servidor");
			System.out.println("2 - Mover board");
			System.out.println("3 - Listar servidores");
			
			option = in.nextInt();
			switch(option){
				case 0:
					break;
				case 1:
					server1 = JOptionPane.showInputDialog("Digite o ip do servidor:");
					admin.addServer(server1);
					break;
				case 2:
					server1 = JOptionPane.showInputDialog("Digite o servidor de origem:"); 
					server2 = JOptionPane.showInputDialog("Digite o servidor de destino:");
					board = JOptionPane.showInputDialog("Digite o nome da board:");
					
					try {
						admin.migrate(server1, server2, board);
					} catch(RemoteException e) {
						e.printStackTrace();
					}

					break;
				case 3:
					admin.list();
					break;
				default:
					System.out.println("Opção não reconhecida!");
					break;	
			}

		}while(option!=0);

		
	}
}