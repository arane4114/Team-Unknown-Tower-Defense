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
				towerDefenseGUI = new TowerDefenseGUI(1, mainMenuGUI, false, null, null, null);
			} else if (e.getX() >= 475 && e.getX() <= 645 && e.getY() >= 333
					&& e.getY() <= 370) {
				setVisible(false);
				towerDefenseGUI = new TowerDefenseGUI(2, mainMenuGUI, false, null, null, null);
			} else if (e.getX() >= 475 && e.getX() <= 645 && e.getY() >= 404
					&& e.getY() <= 440) {
				setVisible(false);
				towerDefenseGUI = new TowerDefenseGUI(3, mainMenuGUI, false, null, null, null);
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
					if(map == 0){
						map = 3;
					} else if(map == 1){
						map = 2;
					}else{
						map = 1;
					}
					towerDefenseGUI = new TowerDefenseGUI(map, mainMenuGUI,
							true, userName, "localhost", server);
				} else{
					String hostAddress = JOptionPane.showInputDialog("Host address: ");
					Object[] options = { "Map 3", "Map 2", "Map 1" };
					int map = JOptionPane.showOptionDialog(new JFrame(),
							"Select your map.", "Multiplayer Setup",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
					if(map == 0){
						map = 3;
					} else if(map == 1){
						map = 2;
					}else{
						map = 1;
					}
					towerDefenseGUI = new TowerDefenseGUI(map, mainMenuGUI,
							true, userName, hostAddress, null);
				}
			} else if (e.getX() >= 475 && e.getX() <= 720 && e.getY() >= 543
					&& e.getY() <= 580) {
				System.out.println("Custom");
			} else if (e.getX() >= 475 && e.getX() <= 690 && e.getY() >= 615
					&& e.getY() <= 655) {
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
