package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import network.TowerServer;

/**
 * This is the main menu for the game. It is also the entry point for the game
 * itself.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class MainMenuGUI extends JFrame {

	private MainMenuPanel mainMenuPanel;
	private TowerDefenseGUI towerDefenseGUI;
	private MainMenuGUI mainMenuGUI;
	private CustomGameGUI customGameGUI;

	/**
	 * Constructor for the main menu.
	 */
	public MainMenuGUI() {
		setTitle("Tower Defense");
		setSize(750, 750);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainMenuPanel = new MainMenuPanel();
		mainMenuPanel.addMouseListener(new mouseListener());
		add(mainMenuPanel);

		setVisible(true);
		mainMenuGUI = this;
	}

	private class mouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getX() >= 475 && e.getX() <= 645 && e.getY() >= 264
					&& e.getY() <= 300) {
				setVisible(false);
				towerDefenseGUI = new TowerDefenseGUI(1, mainMenuGUI, false,
						null, null, null, false, null);
			} else if (e.getX() >= 475 && e.getX() <= 645 && e.getY() >= 333
					&& e.getY() <= 370) {
				setVisible(false);
				towerDefenseGUI = new TowerDefenseGUI(2, mainMenuGUI, false,
						null, null, null, false, null);
			} else if (e.getX() >= 475 && e.getX() <= 645 && e.getY() >= 404
					&& e.getY() <= 440) {
				setVisible(false);
				towerDefenseGUI = new TowerDefenseGUI(3, mainMenuGUI, false,
						null, null, null, false, null);
			} else if (e.getX() >= 475 && e.getX() <= 672 && e.getY() >= 472
					&& e.getY() <= 510) {
				String userName = JOptionPane.showInputDialog("User name:");
				int option = JOptionPane.showConfirmDialog(new JFrame(),
						"Do you want to be the host", "Multiplayer Setup",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					TowerServer server = new TowerServer();
					Object[] options = { "Map 3", "Map 2", "Map 1" };
					int map = JOptionPane.showOptionDialog(new JFrame(),
							"Select your map.", "Multiplayer Setup",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
					if (map == 0) {
						map = 3;
					} else if (map == 1) {
						map = 2;
					} else {
						map = 1;
					}
					towerDefenseGUI = new TowerDefenseGUI(map, mainMenuGUI,
							true, userName, "localhost", server, false, null);
				} else {
					String hostAddress = JOptionPane
							.showInputDialog("Host address: ");
					towerDefenseGUI = new TowerDefenseGUI(1, mainMenuGUI, true,
							userName, hostAddress, null, false, null);
				}
				setVisible(false);
			} else if (e.getX() >= 475 && e.getX() <= 720 && e.getY() >= 543
					&& e.getY() <= 580) {
				Object[] options = { "Map 1", "Map 2", "Map 3" };
				int map = JOptionPane
						.showOptionDialog(new JFrame(), "Select your map.",
								"Multiplayer Setup",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (map == 0) {
					map = 1;
				} else if (map == 1) {
					map = 2;
				} else {
					map = 3;
				}
				customGameGUI = new CustomGameGUI(map, mainMenuGUI);
				setVisible(false);
			} else if (e.getX() >= 475 && e.getX() <= 615 && e.getY() >= 600
					&& e.getY() <= 640) {
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
										+ "Super towers can one shot kill or restore an\n enemy to full health. Use with caution!"
										+ "Play a custom game to create your own map. Note that saving is not supported for custom games.\n"
										+ "Right clicking on a point will bring up more information for that point.",
								"Rules", JOptionPane.PLAIN_MESSAGE);
			} else if (e.getX() >= 475 && e.getX() <= 680 && e.getY() >= 655
					&& e.getY() <= 690) {
				System.exit(0);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	/**
	 * Entry point for the game.
	 * 
	 * @param args
	 *            Not used.
	 */
	public static void main(String[] args) {
		new MainMenuGUI();
	}
}
