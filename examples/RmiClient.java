import java.rmi.Naming;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
	* Classe do cliente que simplesmente manda a requisição e imprime uma mensagem.
	*/
public class RmiClient implements MouseListener {
  private static JFrame frame;
  private int last_x;
  private int last_y;

  /**
   * Código de quando o mouse é executado.
   * @param m O evento lançado pelo mouse.
   * Quando o mouse é pressionado, ele muda de cor.
   */
  public void mousePressed(MouseEvent m) {
  }

  public void mouseReleased(MouseEvent m) {
    int x = m.getX();
    int y = m.getY();
    Graphics g = frame.getGraphics();
    g.setColor(color);
    g.drawLine(last_x, last_y, x, y);
    last_x = x;
    last_y = y;
  }

  public void mouseEntered(MouseEvent m) {
  }

  public void mouseExited(MouseEvent m) {
  }

  public void mouseClicked(MouseEvent m) {
  }

  public RmiClient() {
    this.last_x = 2;
    this.last_y = 25;
    frame = new JFrame("Whiteboard");
    frame.setSize(W, H);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addMouseListener(this);

    try {
      this.obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) throws Exception {
    RmiClient rmi = new RmiClient();
    //Whiteboard w = new Whiteboard();
  }
}