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
	private final int X_BASE = 0;
	private final int Y_BASE = 0;

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

		for (Point p : this.map.getLeftPath()) {
			if(map.isEnemy(p)){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}
		}
		
		for (Point p : this.map.getMiddlePath()) {
			if(map.isEnemy(p)){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}
		}
		
		for (Point p : this.map.getRightPath()) {
			if(map.isEnemy(p)){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}else{
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}
		}
		
		g.setColor(Color.red);
		for (Point p : this.map.getTowers()) {
			g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
		}
		
		g.setColor(Color.white);
		g.fillRect(((map.getGhostTower().x * DELTA_X) + X_BASE),((map.getGhostTower().y * DELTA_Y) + Y_BASE), 10, 10);
		
	}
	
}
