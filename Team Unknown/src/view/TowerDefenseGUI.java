package view;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import map.*;


public class TowerDefenseGUI extends JFrame {
	private static final long serialVersionUID = -1311464600599742255L;

	private Map map;
	
	private GamePlayPanel gamePlayPanel;
	private TowerSelectionPanel towerSelectionPanel; 
	private PlayerInfoPanel playerInfoPanel; 
	private ChatPanel chatPanel; 
	private MiniMapPanel miniMapPanel; 

	public TowerDefenseGUI() {
		
		this.map = new Map(1); //GET SELECTED MAP
		
		setTitle("Tower Defense");
		setSize(750, 540);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePlayPanel = new GamePlayPanel();
		gamePlayPanel.addMouseListener(new mouseListener());
		gamePlayPanel.addMouseMotionListener(new mouseListener());
		
		towerSelectionPanel = new TowerSelectionPanel();
		playerInfoPanel = new PlayerInfoPanel();
		
		JPanel gamePanel = new JPanel();
		gamePanel.add(gamePlayPanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.add(playerInfoPanel, BorderLayout.NORTH);
		infoPanel.add(towerSelectionPanel, BorderLayout.SOUTH);
		
		this.add(gamePanel, BorderLayout.WEST);
		this.add(infoPanel, BorderLayout.EAST);
		
		
		map.addObserver((Observer) gamePlayPanel);
		
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
