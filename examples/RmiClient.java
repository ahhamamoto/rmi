import java.rmi.Naming;

/**
	* Classe do cliente que simplesmente manda a requisição e imprime uma mensagem.
	*/
public class RmiClient {
	/**
		* Método main que cria o objeto e manda a requisição.
		*/
	public static void main(String args[]) throws Exception {
		RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
		System.out.println(obj.getMessage());
    obj.sendCoordinate(2, 3);
    int[] coor = obj.getLastCoordinate();
    System.out.println(coor[0] + " " + coor[1]);
	}
}