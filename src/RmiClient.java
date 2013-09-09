import java.rmi.*;
import java.rmi.server.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RmiClient extends UnicastRemoteObject implements IClient, MouseListener {
	private static JFrame frame; /**< JFrame que vai conter os pontos. */
	private static int W = 600, H = 480; /**< Largura e altura da janela do JFrame. */
	private Color color; /**< A cor das linhas desenhadas por esse cliente. */
	private IServer server = null; /**< Variável para o servidor. */
	private int last_x; /**< Valor do último ponto na coordenada x. */
	private int last_y; /**< Valor do último ponto na coordenada y. */
	private String board; /**< Nome da board a qual o cliente faz parte. */
	private String ip;

	public void mousePressed(MouseEvent m) {
	}
	
	/**
	 * Método que é executado quando o mouse é soltado. Pega as 
	 * coordenadas atuais do cursor e desenha a linha no frame
	 * e manda a linha desenhada para o servidor.
	 * @param m Pega o evento disparado como parâmetro.
	 */
	public void mouseReleased(MouseEvent m) {
		int x = m.getX();
		int y = m.getY();
		Graphics g = frame.getGraphics();
		g.setColor(color);
		g.drawLine(last_x, last_y, x, y);
		try{
			server.sendLine(last_x, last_y, x, y, this.board);
		} catch(RemoteException e){
			e.printStackTrace();
		}
		last_x = x;
		last_y = y;
	}

	public void mouseEntered(MouseEvent m) {
	}

	public void mouseExited(MouseEvent m) {
	}

	public void mouseClicked(MouseEvent m) {
	}

	public void drawLine(int x1, int y1, int x2, int y2) throws RemoteException{
		Graphics g = frame.getGraphics();
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
		this.last_x = x2;
		this.last_y = y2;
	}
	
	public void changeServer(String new_server) throws RemoteException {
		try {
			server = (IServer)Naming.lookup("//" + new_server + "/RmiServer");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RmiClient(String ip, String board) throws RemoteException {
		// this.last_x = 2;
		// this.last_y = 25;
		this.ip = ip;
		this.board = board;
		frame = new JFrame("Whiteboard");
		frame.setSize(W, H);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(this);

		try {
			server = (IServer)Naming.lookup("//" + this.ip + "/RmiServer");
			server.setClient(this, board);
			this.last_x = 2;
			this.last_y = 25;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		try {
			if (args.length == 2) {
				RmiClient rmi = new RmiClient(args[0], args[1]);
			}
		} catch(RemoteException e){
			e.printStackTrace();
		}
	}
}