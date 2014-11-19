package view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Map;

public class GamePlayPanel extends JPanel implements Observer{

	
	private Map map;
	
	private final int DELTA_X = 10;
	private final int DELTA_Y = 10;
	private final int X_BASE = 5;
	private final int Y_BASE = 5;

	public GamePlayPanel(){
		
		this.setPreferredSize(new Dimension(500, 500));
		
	}

	@Override
	public void update(Observable o, Object unsed) {
		Map map = (Map) o;
		this.map = map;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(X_BASE, Y_BASE, 500, 500);
		g.setColor(Color.orange);

		for (Point p : this.map.getLeftPath()) {
			g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
		}
		
		for (Point p : this.map.getMiddlePath()) {
			g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
		}
		
		for (Point p : this.map.getRightPath()) {
			g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
		}
	}
	
}
