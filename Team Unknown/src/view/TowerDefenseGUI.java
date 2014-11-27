package view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observer;

import javax.swing.JFrame;

import map.*;


public class TowerDefenseGUI extends JFrame {
	private static final long serialVersionUID = -1311464600599742255L;

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
		gamePanel.addMouseMotionListener(new mouseListener());
		
		this.add(gamePanel);
		
		map.addObserver((Observer) gamePanel);
		
		setVisible(true);
		this.map.forceUpdate();
	}
	
	private class mouseListener implements MouseListener, MouseMotionListener{

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			Point p = new Point(e.getX() / 10, e.getY() / 10);
			if(map.isValid(p) && !map.isPath(p) && !map.isTower(p) && map.getPlayer().canBuy(5)){
				map.getPlayer().buy(5);
				map.setTower(p);
				List<Point> list = map.getTowers();
				for(Point p1: list){
					System.out.println("Tower at: "+p1);
				}
				//Enemy g = new Grunt(1,map);
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {
			Point p = new Point(e.getX() / 10, e.getY() / 10);
			if(map.isValid(p) && !map.isPath(p) && !map.isTower(p)){
				map.setGhostTower(p);
				repaint();
			}else{
				map.setGhostTower(new Point(-1, -1));
				repaint();
			}
		}
	}

	public static void main(String[] args) {
		new TowerDefenseGUI();
	}	
}
