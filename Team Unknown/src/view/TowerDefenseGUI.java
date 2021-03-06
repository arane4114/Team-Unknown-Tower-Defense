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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
@SuppressWarnings("unused")
public class TowerDefenseGUI extends JFrame {

	private Map map;

	private String currentString = "Neutral Tower";

	private static String neutralTower = "Neutral Tower";
	private static String fireTower = "Fire Tower";
	private static String waterTower = "Water Tower";
	private static String stoneTower = "Stone Tower";
	private static String superTower = "Super Tower";

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
	private int mapId;
	private boolean wasLoadedFromDisk;
	private JLabel errorLabel;
	private boolean isCustomMap;
	private List<Point> customMapPoints;

	/**
	 * Creates GUI by request of user.
	 * 
	 * @param mapSelected
	 *            The map to display.
	 * @param mainMenuGUI
	 *            A link back to the main menu for end of game events/
	 * @param isMultiplayer
	 *            True if a multiplayer game is selected.
	 * @param clientName
	 *            The name of the user in a online room.
	 * @param host
	 *            The address of the host.
	 * @param server
	 *            A link to store the server if it was created on this machine.
	 * @param isCustomMap
	 *            Checks if the map is a custom map
	 * @param customMapPoints
	 *            A list of custom map points.
	 */
	public TowerDefenseGUI(int mapSelected, MainMenuGUI mainMenuGUI,
			boolean isMultiplayer, String clientName, String host,
			TowerServer server, boolean isCustomMap, List<Point> customMapPoints) {
		this.mapId = mapSelected;
		if (!isMultiplayer && !isCustomMap) {
			int decision = JOptionPane.showConfirmDialog(null, "Load Data?",
					"Load Data?", JOptionPane.YES_NO_OPTION);
			if (!(decision == JOptionPane.YES_OPTION && loadData())) {
				this.map = new Map(this);
			}
		} else {
			this.map = new Map(this);
		}
		this.mainMenuGUI = mainMenuGUI;
		this.multiplayer = isMultiplayer;
		this.isCustomMap = isCustomMap;
		this.customMapPoints = customMapPoints;

		setTitle("Tower Defense");
		setSize(1030, 810);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (isMultiplayer) {
			this.server = server;
			this.miniMapPanel = new MiniMapPanel();
			this.towerClient = new TowerClient(clientName, host,
					map.getPlayer(), this.miniMapPanel, mapSelected, this);
			this.chatPanel = this.towerClient.getChatPanel();

			JPanel multiplayerPanel = new JPanel();
			multiplayerPanel.setPreferredSize(new Dimension(260, 750));
			multiplayerPanel.add(chatPanel, BorderLayout.NORTH);
			multiplayerPanel.add(new SendMoneyPanel(map.getPlayer(),
					towerClient), BorderLayout.CENTER);
			multiplayerPanel.add(this.miniMapPanel, BorderLayout.SOUTH);

			this.add(multiplayerPanel, BorderLayout.EAST);
			this.setSize(new Dimension(1260, 810));

			if (server == null) {
				this.towerClient.getMapId();
			} else {
				finishSettingUpGUI();
			}

		} else {
			finishSettingUpGUI();
		}
	}

	private void finishSettingUpGUI() {
		map.setMapId(this.mapId);

		if (isCustomMap && !customMapPoints.isEmpty()) {
			map.overridePoints(customMapPoints);
		}

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

		JRadioButton towerOneButton = new JRadioButton(neutralTower);
		towerOneButton.setActionCommand(neutralTower);
		towerOneButton.setSelected(true);
		towerOneButton.addActionListener(new towerButtonListener());
		group.add(towerOneButton);
		buttonPanel.add(towerOneButton);

		JRadioButton towerTwoButton = new JRadioButton(fireTower);
		towerTwoButton.setActionCommand(fireTower);
		towerTwoButton.addActionListener(new towerButtonListener());
		group.add(towerTwoButton);
		buttonPanel.add(towerTwoButton);

		JRadioButton towerThreeButton = new JRadioButton(waterTower);
		towerThreeButton.setActionCommand(waterTower);
		towerThreeButton.addActionListener(new towerButtonListener());
		group.add(towerThreeButton);
		buttonPanel.add(towerThreeButton);

		JRadioButton towerFourButton = new JRadioButton(stoneTower);
		towerFourButton.setActionCommand(stoneTower);
		towerFourButton.addActionListener(new towerButtonListener());
		group.add(towerFourButton);
		buttonPanel.add(towerFourButton);

		JRadioButton towerFiveButton = new JRadioButton(superTower);
		towerFiveButton.setActionCommand(superTower);
		towerFiveButton.addActionListener(new towerButtonListener());
		group.add(towerFiveButton);
		buttonPanel.add(towerFiveButton);

		gamePlayPanel = new GamePlayPanel(mapId, this, towerClient);
		gamePlayPanel.addMouseListener(new mouseListener());
		gamePlayPanel.addMouseMotionListener(new mouseListener());

		towerSelectionPanel = new TowerSelectionPanel();
		playerInfoPanel = new PlayerInfoPanel();
		this.cellInfoPanel = new CellInfoPanel(this.map);

		JPanel gamePanel = new JPanel();
		gamePanel.add(gamePlayPanel);

		JPanel infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(260, 750));
		infoPanel.add(playerInfoPanel, BorderLayout.NORTH);
		infoPanel.add(buttonPanel, BorderLayout.CENTER);
		infoPanel.add(cellInfoPanel, BorderLayout.SOUTH);

		this.add(gamePanel, BorderLayout.CENTER);
		this.add(infoPanel, BorderLayout.WEST);

		map.addObserver((Observer) playerInfoPanel); // ADDed observer
		map.addObserver((Observer) gamePlayPanel);

		if (wasLoadedFromDisk) {
			resume();
		}

		setVisible(true);
		this.map.forceUpdate();
		if (multiplayer) {
			this.miniMapPanel
					.setBufferedImage(this.gamePlayPanel.getMapImage());
		}

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				if (multiplayer) {
					towerClient.willClose();
				} else if (isCustomMap) {
				} else {
					int decision = JOptionPane.showConfirmDialog(null,
							"Save Data?", "Save Data?",
							JOptionPane.YES_NO_OPTION);
					if (decision == JOptionPane.YES_OPTION) {
						saveData();
					}
				}
			}
		});
	}

	private void saveData() {
		try {
			pause();
			FileOutputStream fileOut = new FileOutputStream(new File("Map"
					+ mapId + ".dat"));
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(map);
			objectOut.close();
		} catch (Exception e) {
		}

	}

	private boolean loadData() {
		try {
			FileInputStream fileIn = new FileInputStream(new File("Map" + mapId
					+ ".dat"));
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			map = (Map) objectIn.readObject();
			objectIn.close();
			map.wasLoadedFromDisk(this);
			this.wasLoadedFromDisk = true;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Allows other classes to get access to the multiplayer client.
	 * 
	 * @return The tower client object. Null if not in multiplayer.
	 */
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

	private void pause() {
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
	}

	private void resume() {
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

	private class mouseListener implements MouseListener, MouseMotionListener {

		/**
		 * Handles all mouse clicks on the game map.
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			Point p = new Point(e.getX() / 15, e.getY() / 15);
			if (map.isValid(p)) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (currentString.equals(neutralTower)) {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(5)) {
							map.getPlayer().buy(5);
							map.setTower(1, p);
							repaint();
						}
					} else if (currentString.equals(fireTower)) {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(10)) {
							map.getPlayer().buy(10);
							map.setTower(2, p);
							repaint();
						}
					} else if (currentString.equals(waterTower)) {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(10)) {
							map.getPlayer().buy(10);
							map.setTower(3, p);
							repaint();
						}
					} else if (currentString.equals(stoneTower)) {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(10)) {
							map.getPlayer().buy(10);
							map.setTower(4, p);
							repaint();
						}
					} else if (currentString.equals(superTower)) {
						if (!map.isPath(p) && !map.isTower(p)
								&& map.getPlayer().canBuy(20)) {
							map.getPlayer().buy(15);
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
										+ "which can be used to buy or upgrade towers.\n"
										+ "Fire towers are good against stone enemies (grey) \n"
										+ "Water towers are good against fire enemies (red) \n"
										+ "Stone towers are good againt water enemies (blue) \n"
										+ "Nuetral towers are average agains all types \n"
										+ "Super towers can one shot kill or restore an\n enemy to full health. Use with caution!\n"
										+ "Right clicking on a point will provide information on that point.",
								"Rules", JOptionPane.PLAIN_MESSAGE);
			} else if (e.getSource() == menuItemSpeed) {
				System.out.println("SPEED");
			} else if (e.getSource() == menuItemSave) {
				System.out.println("Save");
			} else {
				if (!multiplayer) {
					pause();

					JOptionPane.showMessageDialog(new JFrame(),
							"Continue to end pause.", "Paused",
							JOptionPane.PLAIN_MESSAGE);

					resume();
				}

			}

		}
	}

	/**
	 * Used in a multiplayer scenario where player two needs to find what map
	 * the host is playing on. This method sets of the finish setting up GUI
	 * method as the second half of init depends on map id being valid.
	 * 
	 * @param mapId2
	 *            The map id of the host.
	 */
	public void setMapId(Integer mapId2) {
		this.mapId = mapId2;
		this.finishSettingUpGUI();
	}
}
