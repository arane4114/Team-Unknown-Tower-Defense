package view;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import map.*;


public class TowerDefenseGUI extends JFrame {
	private static final long serialVersionUID = -1311464600599742255L;


	private Map map;
	
	private String currentString = "Tower One";
	
	private static String towerOne = "Tower One";
	private static String towerTwo = "Tower Two";
	private static String twoerThree = "Tower Three";
	
	private GamePlayPanel gamePlayPanel;
	private TowerSelectionPanel towerSelectionPanel; 
	private PlayerInfoPanel playerInfoPanel; 
	private ChatPanel chatPanel; 
	private MiniMapPanel miniMapPanel; 

	public TowerDefenseGUI() {
		
		this.map = new Map(3); //GET SELECTED MAP
		
		setTitle("Tower Defense");
		setSize(750, 560);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem menu = new JMenu("Menu");
		JMenuItem menuItemRules = new JMenuItem("Rules");
		JMenuItem menuItemPause = new JMenuItem("Pause");
		JMenuItem menuItemSpeed = new JMenuItem("Speed");
		menu.add(menuItemRules);
		menu.add(menuItemPause);
		menu.add(menuItemSpeed);
		setJMenuBar(menuBar);
		menuBar.add(menu);
		
		ButtonGroup group = new ButtonGroup();
		JPanel buttonPanel = new JPanel(new GridLayout(3, 0));

		JRadioButton towerOneButton = new JRadioButton(towerOne);
		towerOneButton.setActionCommand(towerOne);
		towerOneButton.setSelected(true);
		towerOneButton.addActionListener(new buttonListener());
		group.add(towerOneButton);
		buttonPanel.add(towerOneButton);

		JRadioButton towerTwoButton = new JRadioButton(towerTwo);
		towerTwoButton.setActionCommand(towerTwo);
		towerTwoButton.addActionListener(new buttonListener());
		group.add(towerTwoButton);
		buttonPanel.add(towerTwoButton);

		JRadioButton towerThreeButton = new JRadioButton(twoerThree);
		towerThreeButton.setActionCommand(twoerThree);
		towerThreeButton.addActionListener(new buttonListener());
		group.add(towerThreeButton);
		buttonPanel.add(towerThreeButton);

		
		gamePlayPanel = new GamePlayPanel();
		gamePlayPanel.addMouseListener(new mouseListener());
		gamePlayPanel.addMouseMotionListener(new mouseListener());
		
		towerSelectionPanel = new TowerSelectionPanel();
		playerInfoPanel = new PlayerInfoPanel();
		
		JPanel gamePanel = new JPanel();
		gamePanel.add(gamePlayPanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.add(playerInfoPanel, BorderLayout.NORTH);
		infoPanel.add(buttonPanel, BorderLayout.SOUTH);
		
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
	
	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currentString = e.getActionCommand();
		}
	}

	public static void main(String[] args) {
		new TowerDefenseGUI();
	}	
}
