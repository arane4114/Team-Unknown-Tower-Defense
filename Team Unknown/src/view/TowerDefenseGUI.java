package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.SwingUtilities;

import model.Map;
import network.TowerClient;
import network.TowerServer;
import enemy.Enemy;

/**
 * This handles all interactions between the player and a running game.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class TowerDefenseGUI extends JFrame {

	private Map map;

	private String currentString = "Tower One";

	private static String towerOne = "Tower One";
	private static String towerTwo = "Tower Two";
	private static String towerThree = "Tower Three";
	private static String towerFour = "Tower Four";
	private static String towerFive = "Tower Five";

	private GamePlayPanel gamePlayPanel;
	private TowerSelectionPanel towerSelectionPanel;
	private PlayerInfoPanel playerInfoPanel;
	private ChatPanel chatPanel;
	private MiniMapPanel miniMapPanel;
	private MainMenuGUI mainMenuGUI;
	private CellInfoPanel cellInfoPanel;
	private TowerServer server;

	private JPanel buttonPanel;

	private TowerClient towerClient;

	private JMenuItem menuItemRules;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemSpeed;
	private JMenuItem menuItemSave;

	private boolean multiplayer;

	/**
	 * Creates and assembles all the panels needed for a game.
	 * 
	 * @param mapSelected
	 *            The map that is to be played on.
	 * @param mainMenuGUI
	 *            A call back for end of game events.
	 */
	public TowerDefenseGUI(int mapSelected, MainMenuGUI mainMenuGUI,
			boolean isMultiplayer, String clientName, String host,
			TowerServer server) {
		this.map = new Map(mapSelected, this);
		this.mainMenuGUI = mainMenuGUI;
		this.multiplayer = multiplayer;

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
		buttonPanel = new JPanel(new GridLayout(3, 0));

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

		JRadioButton towerThreeButton = new JRadioButton(towerThree);
		towerThreeButton.setActionCommand(towerThree);
		towerThreeButton.addActionListener(new towerButtonListener());
		group.add(towerThreeButton);
		buttonPanel.add(towerThreeButton);

		JRadioButton towerFourButton = new JRadioButton(towerFour);
		towerFourButton.setActionCommand(towerFour);
		towerFourButton.addActionListener(new towerButtonListener());
		group.add(towerFourButton);
		buttonPanel.add(towerFourButton);

		JRadioButton towerFiveButton = new JRadioButton(towerFive);
		towerFiveButton.setActionCommand(towerFive);
		towerFiveButton.addActionListener(new towerButtonListener());
		group.add(towerFiveButton);
		buttonPanel.add(towerFiveButton);

		if (isMultiplayer) {
			this.server = server;
			this.miniMapPanel = new MiniMapPanel();
			this.towerClient = new TowerClient(clientName, host,
					map.getPlayer(), this.miniMapPanel);
			this.chatPanel = this.towerClient.getChatPanel();

			JPanel multiplayerPanel = new JPanel();
			multiplayerPanel.setPreferredSize(new Dimension(250, 750));
			multiplayerPanel.add(chatPanel, BorderLayout.NORTH);
			multiplayerPanel.add(new SendMoneyPanel(map.getPlayer(),
					towerClient), BorderLayout.CENTER);
			multiplayerPanel.add(this.miniMapPanel, BorderLayout.SOUTH);

			this.add(multiplayerPanel, BorderLayout.EAST);
			this.setSize(new Dimension(1250, 810));

			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent arg0) {
					towerClient.willClose();
				}
			});

		}

		gamePlayPanel = new GamePlayPanel(mapSelected, this, towerClient);
		gamePlayPanel.addMouseListener(new mouseListener());
		gamePlayPanel.addMouseMotionListener(new mouseListener());

		towerSelectionPanel = new TowerSelectionPanel();
		playerInfoPanel = new PlayerInfoPanel();
		this.cellInfoPanel = new CellInfoPanel(this.map);

		JPanel gamePanel = new JPanel();
		gamePanel.add(gamePlayPanel);

		JPanel infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(250, 750));
		infoPanel.add(playerInfoPanel, BorderLayout.NORTH);
		infoPanel.add(buttonPanel, BorderLayout.CENTER);
		infoPanel.add(cellInfoPanel, BorderLayout.SOUTH);

		this.add(gamePanel, BorderLayout.CENTER);
		this.add(infoPanel, BorderLayout.WEST);

		map.addObserver((Observer) playerInfoPanel); // ADDed observer
		map.addObserver((Observer) gamePlayPanel);

		setVisible(true);
		this.map.forceUpdate();
		this.miniMapPanel.setBufferedImage(this.gamePlayPanel.getMapImage());
	}

	public TowerClient getTowerClient() {
		return this.towerClient;
	}

	/**
	 * A way to hide this panel.
	 */
	public void visibleFalse() {
		setVisible(false);
	}

	/**
	 * Displays the main menu at end of game.
	 */
	public void mainMenuVisible() {
		mainMenuGUI.setVisible(true);
	}

	private class mouseListener implements MouseListener, MouseMotionListener {

		/**
		 * Handles all mouse clicks on the game map.
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			Point p = new Point(e.getX() / 15, e.getY() / 15);
			if (map.isValid(p)) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (currentString == "Tower One") {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(5)) {
							map.getPlayer().buy(5);
							map.setTower(1, p);
							repaint();
						}
					} else if (currentString == "Tower Two") {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(10)) {
							map.getPlayer().buy(10);
							map.setTower(2, p);
							repaint();
						}
					} else if (currentString == "Tower Three") {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(10)) {
							map.getPlayer().buy(15);
							map.setTower(3, p);
							repaint();
						}
					} else if (currentString == "Tower Four") {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(10)) {
							map.getPlayer().buy(20);
							map.setTower(4, p);
							repaint();
						}
					} else if (currentString == "Tower Five") {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(20)) {
							map.getPlayer().buy(10);
							map.setTower(5, p);
							repaint();
						}
					}
				} else if (SwingUtilities.isRightMouseButton(e)) {
					cellInfoPanel.setPoint(p);
					if (map.isTower(p)) {
						List<Point> pointsInRange = map.getTower(p)
								.getPointsInRange();
						gamePlayPanel.setPointsInRange(pointsInRange);
						repaint();
					} else {
						gamePlayPanel.setPointsInRange(null);
						repaint();
					}
				} else {
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

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

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == menuItemRules) {
				JOptionPane
						.showMessageDialog(
								new JFrame(),
								"The goal of tower defense is to try to stop enemies \n"
										+ "from crossing the map by building towers which shoot \n"
										+ "at them as they pass. Enemies and towers \n"
										+ "have varied abilities, costs, and upgrade prices. \n"
										+ "When an enemy is defeated, the player earns money, \n"
										+ "which can be used to buy or upgrade towers.",
								"Rules", JOptionPane.PLAIN_MESSAGE);
			} else if (e.getSource() == menuItemSpeed) {
				System.out.println("SPEED");
			} else if (e.getSource() == menuItemSave) {
				System.out.println("Save");
			} else {
				if (!multiplayer) {
					for (Point p : map.getLeftPath()) {
						if (map.isEnemy(p)) {
							for (Enemy enemy : map.getListOfEnemies(p)) {
								enemy.pause();
							}
						}
					}

					for (Point p : map.getMiddlePath()) {
						if (map.isEnemy(p)) {
							for (Enemy enemy : map.getListOfEnemies(p)) {
								enemy.pause();
							}
						}
					}

					for (Point p : map.getRightPath()) {
						if (map.isEnemy(p)) {
							for (Enemy enemy : map.getListOfEnemies(p)) {
								enemy.pause();
							}
						}
					}

					map.getEnemySpawner().pause();

					for (Point t : map.getTowers()) {
						map.getTower(t).pause();
					}

					JOptionPane.showMessageDialog(new JFrame(),
							"Continue to end pause.", "Paused",
							JOptionPane.PLAIN_MESSAGE);

					for (Point p : map.getLeftPath()) {
						if (map.isEnemy(p)) {
							for (Enemy enemy : map.getListOfEnemies(p)) {
								enemy.resume();
							}
						}
					}

					for (Point p : map.getMiddlePath()) {
						if (map.isEnemy(p)) {
							for (Enemy enemy : map.getListOfEnemies(p)) {
								enemy.resume();
							}
						}
					}

					for (Point p : map.getRightPath()) {
						if (map.isEnemy(p)) {
							for (Enemy enemy : map.getListOfEnemies(p)) {
								enemy.resume();
							}
						}
					}

					map.getEnemySpawner().resume();

					for (Point t : map.getTowers()) {
						map.getTower(t).resume();
					}
				}

			}

		}
	}
}
