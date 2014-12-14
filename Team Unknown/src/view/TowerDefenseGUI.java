package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import map.Map;
import controller.NRCClient;

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

	private NRCClient chatClient;
	
	private JMenuItem menuItemRules;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemSpeed;
	private JMenuItem menuItemSave;

	public TowerDefenseGUI(int mapSelected) {

		this.map = new Map(mapSelected);

		setTitle("Tower Defense");
		setSize(1000, 810);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ActionListener buttonListener = new ButtonListener();
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem menu = new JMenu("Menu");
		menuItemRules = new JMenuItem("Rules");
		menuItemRules.addActionListener(buttonListener);
		menuItemPause = new JMenuItem("Pause");
		menuItemPause.addActionListener(buttonListener);
		menuItemSpeed = new JMenuItem("Speed");
		menuItemSpeed.addActionListener(buttonListener);
		menuItemSave = new JMenuItem("Save");
		menuItemSave.addActionListener(buttonListener);
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
		towerOneButton.addActionListener(new towerButtonListener());
		group.add(towerOneButton);
		buttonPanel.add(towerOneButton);

		JRadioButton towerTwoButton = new JRadioButton(towerTwo);
		towerTwoButton.setActionCommand(towerTwo);
		towerTwoButton.addActionListener(new towerButtonListener());
		group.add(towerTwoButton);
		buttonPanel.add(towerTwoButton);

		JRadioButton towerThreeButton = new JRadioButton(twoerThree);
		towerThreeButton.setActionCommand(twoerThree);
		towerThreeButton.addActionListener(new towerButtonListener());
		group.add(towerThreeButton);
		buttonPanel.add(towerThreeButton);

	//	this.chatClient = new NRCClient();
		//this.chatPanel = this.chatClient.getChatPanel();

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
		
		//infoPanel.add(chatPanel);

		this.add(gamePanel, BorderLayout.WEST);
		this.add(infoPanel, BorderLayout.EAST);

		map.addObserver((Observer) gamePlayPanel);

//		this.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent arg0) {
//				chatClient.willClose();
//			}
//		});

		setVisible(true);
		this.map.forceUpdate();
	}

	private class mouseListener implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Point p = new Point(e.getX() / 15, e.getY() / 15);
			if (map.isValid(p) && !map.isPath(p) && !map.isTower(p)
					&& map.getPlayer().canBuy(5)) {
				map.getPlayer().buy(5);
				map.setTower(p);
				List<Point> list = map.getTowers();
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Point p = new Point(e.getX() / 15, e.getY() / 15);
			if (map.isValid(p) && !map.isPath(p) && !map.isTower(p)) {
				map.setGhostTower(p);
				repaint();
			} else {
				map.setGhostTower(new Point(-1, -1));
				repaint();
			}
		}
	}

	private class towerButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currentString = e.getActionCommand();
		}
	}

	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == menuItemRules){
				JOptionPane.showMessageDialog(new JFrame(),
					    "...............",
					    "Rules",
					    JOptionPane.PLAIN_MESSAGE);
			}else if(e.getSource() == menuItemSpeed){
				System.out.println("SPEED");
			}else if(e.getSource() == menuItemSave){
				System.out.println("Save");
			}else{
				JOptionPane.showMessageDialog(new JFrame(),
					    "Continue to end pause.",
					    "Paused",
					    JOptionPane.PLAIN_MESSAGE);
			}
			
		}
	}
}
