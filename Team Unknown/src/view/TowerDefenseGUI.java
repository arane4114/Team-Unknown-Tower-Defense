package view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

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
		public void mouseClicked(MouseEvent e) {
			Point p = new Point(e.getX() / 10, e.getY() / 10);
			if(map.isValid(p)){
			map.setTower(p); //Dont know what it should be just a hard code.
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public static void main(String[] args) {
		new TowerDefenseGUI();
	}	
}
