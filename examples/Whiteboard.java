import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Whiteboard {
	private static JFrame frame;
	private static int W = 600, H = 480;
	private int last_x;
	private int last_y;
	
	MouseListener getCoords = new MouseAdapter() {
		public void mouseClicked(MouseEvent m) {
			int x = m.getX();
			int y = m.getY();
			Graphics g = frame.getGraphics();
			g.drawLine(last_x, last_y, x, y);
			last_x = x;
			last_y = y;
		}
	};
	
	public Whiteboard() {
		this.last_x = 2;
		this.last_y = 25;
		frame = new JFrame("Whiteboard");
		frame.setSize(W, H);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(getCoords);
	}
	
	public static void main(String args[]) {
		Whiteboard w = new Whiteboard();
	}
}