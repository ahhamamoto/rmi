import java.rmi.*;
import java.rmi.server.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RmiClient extends UnicastRemoteObject implements IClient, MouseListener {
  private static JFrame frame;
  private static int W = 600, H = 480;
  private Color color;
  IServer server = null;
  private int last_x;
  private int last_y;

  public void mousePressed(MouseEvent m) {
  }

  public void mouseReleased(MouseEvent m) {
    int x = m.getX();
    int y = m.getY();
    Graphics g = frame.getGraphics();
    g.setColor(color);
    g.drawLine(last_x, last_y, x, y);
    try{
      server.sendLine(last_x, last_y, x, y, this);
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
    //last_x = x1;
    //last_y = y1;

    System.out.println("Server received line: "+x1+" "+y1+" "+x2+" "+y2);
  }


  public RmiClient() throws RemoteException{
    this.last_x = 2;
    this.last_y = 25;
    frame = new JFrame("Whiteboard");
    frame.setSize(W, H);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addMouseListener(this);

    try {
      server= (IServer)Naming.lookup("//localhost/RmiServer");
      server.setClient(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) throws Exception {
    try{
      RmiClient rmi = new RmiClient();
    } catch(RemoteException e){
      e.printStackTrace();
    }
    //Whiteboard w = new Whiteboard();
  }
}