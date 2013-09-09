import java.rmi.*;

public interface IClient extends Remote{
	
	/**
	 * Método para desenhar uma linha em um dos cliente do servidor.
	 * @param x1 Coordenada x do ponto 1.
	 * @param y1 Coordenada y do ponto 1.
	 * @param x2 Coordenada x do ponto 2.
	 * @param y2 Coordenada y do ponto 2.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) throws RemoteException;
}