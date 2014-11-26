package view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

import model.Enemy;
import model.Grunt;
import model.Map;

import javax.swing.JFrame;


public class TowerDefenseGUI extends JFrame {
	private Map map;
	
	private GamePlayPanel gamePanel; 

	public TowerDefenseGUI() {
		
		this.map = new Map();
		
		setTitle("Tower Defense");
		setSize(500, 520);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel = new GamePlayPanel();
		gamePanel.addMouseListener(new mouseListener());
		
		this.add(gamePanel);
		
		map.addObserver((Observer) gamePanel);
		
		setVisible(true);
		this.map.forceUpdate();
	}
	
	private class mouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			Point p = new Point(e.getX() / 10, e.getY() / 10);
			if(map.isValid(p) && !map.isPath(p) && !map.isTower(p)){
				map.setTower(p);
				Enemy g = new Grunt(1,map);
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}

	public static void main(String[] args) {
		new TowerDefenseGUI();
	}	
}
