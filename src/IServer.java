import java.rmi.*;

public interface IServer extends Remote{
	
	/**
	 * Método que adiciona o cliente na lista do servidor e retorna 
	 * a última coordenada desenhada.
	 * @param client Manda o cliente como parâmetro.
	 */
	public void setClient(IClient client, String board) throws RemoteException;

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
	public void sendLine(int x1, int y1, int x2, int y2, String board) throws RemoteException;
	
	public void changeBoard(String server, String board) throws RemoteException;
	
	public void setBoard(String server, Board board) throws RemoteException;
	
}