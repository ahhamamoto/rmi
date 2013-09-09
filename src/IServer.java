import java.rmi.*;

public interface IServer extends Remote{
	
	/**
	 * Método que adiciona o cliente na lista do servidor e retorna 
	 * a última coordenada desenhada.
	 * @param client Manda o cliente como parâmetro.
	 */
	public int[] setClient(IClient client) throws RemoteException;

	/**
	 * Método que envia a linha desenahada em um dos clientes para o servidor.
	 * @param x1 Coordenada x do ponto 1.
	 * @@aram y1 Coordenada y do ponto 1.
	 * @param x2 Coordenada x do ponto 2.
	 * @param y2 Coordenada y do ponto 2.
	 */
	public void sendLine(int x1, int y1, int x2, int y2) throws RemoteException;
}