import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Whiteboard implements MouseListener {
	private static JFrame frame;
	private static int W = 600, H = 480;
	private Color color;
	private int last_x;
	private int last_y;
	
	public void mousePressed(MouseEvent m) {
		if (color.getRed() == 255) 
			color = color.green;
			// color = new Color(0, 255, 0);
		else if (color.getGreen() == 255) 
			color = color.blue;
		else 
			color = color.red;
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
	
	public Whiteboard() {
		this.last_x = 2;
		this.last_y = 25;
		this.color = new Color(255, 0, 0);
		frame = new JFrame("Whiteboard");
		frame.setSize(W, H);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(this);
	}
	
	public static void main(String args[]) {
		Whiteboard w = new Whiteboard();
	}
}