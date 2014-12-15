package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MainMenuGUI extends JFrame {
	
	private MainMenuPanel mainMenuPanel;
	private TowerDefenseGUI towerDefenseGUI; 
	private MainMenuGUI mainMenuGUI;
	
	public MainMenuGUI(){
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
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getX() >= 475 && e.getX() <= 645 && e.getY() >= 264 && e.getY() <= 300){
				setVisible(false);
				towerDefenseGUI = new TowerDefenseGUI(1, mainMenuGUI);
			}else if(e.getX() >= 475 && e.getX() <= 645 && e.getY() >= 333 && e.getY() <= 370){
				setVisible(false);
				towerDefenseGUI = new TowerDefenseGUI(2, mainMenuGUI);
			}else if(e.getX() >= 475 && e.getX() <= 645 && e.getY() >= 404 && e.getY() <= 440){
				setVisible(false);
				towerDefenseGUI = new TowerDefenseGUI(3, mainMenuGUI);
			}else if(e.getX() >= 475 && e.getX() <= 672 && e.getY() >= 472 && e.getY() <= 510){
				System.out.println("2 Player");
			}else if(e.getX() >= 475 && e.getX() <= 720 && e.getY() >= 543 && e.getY() <= 580){
				System.exit(0);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

	}
	
	public static void main(String[] args) {
		new MainMenuGUI();
	}
}

