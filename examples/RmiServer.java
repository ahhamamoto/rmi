import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

/**
	* A classe do servidor do RMI.
	* Essa classe simplesmente imprime uma mensagem indicando
	* que recebeu uma mensagem do cliente e manda uma mensagem
	* de volta para o cliente.
	*/
public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {
	private String message;

	/**
		* Construtor da classe.
		*/
	public RmiServer(String str) throws RemoteException {
    super(0);
    this.message = str;
	}
	
	/**
		* Método que imprime uma mensagem e retorna uma string pro cliente.
		* Esse método imprime uma mensagem indicando que recebeu uma 
		* requisição do cliente e manda uma mensagem de volta pro
		* cliente.
		* @return Retorna a variável estática MESSAGE para o cliente.
		*/
	public String getMessage() {
		System.out.println("getMessage() method requested");
		return this.message;
	}
<<<<<<< HEAD

  public int[] getLastCoordinate() {
    return coordinate;
  }

  public void sendCoordinate(int x, int y) {
    coordinate[0] = x;
    coordinate[1] = y;
    System.out.println("New coordinates received: " + x +" " + y);
  }
=======
>>>>>>> 451a81876292e9445a8e6fc56b464fa81364c709
	
	/**
		* Método main que cria o registro e binda o programa com o localhost.
		*/
	public static void main(String args[]) throws Exception {
		System.out.println("RMI Server started.");
		
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry started");
		} catch (RemoteException e) {
			System.out.println("RMI registry already exists");
		}
		
		RmiServer obj = new RmiServer("Hello!");
		
		Naming.rebind("//localhost/RmiServer", obj);
		System.out.println("PeerServer bound in registry");
	}
}