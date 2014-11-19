package view;

import java.util.Observer;

import model.Map;

import javax.swing.JFrame;

public class TowerDefenseGUI extends JFrame {
	private Map map;
	
	private GamePlayPanel gamePanel = new GamePlayPanel();

	public TowerDefenseGUI() {
		
		this.map = new Map();
		
		setTitle("Tower Defense");
		setSize(515, 535);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(gamePanel);
		
		map.addObserver((Observer) gamePanel);
		
		setVisible(true);
		this.map.forceUpdate();
	}

	public static void main(String[] args) {
		new TowerDefenseGUI();
	}	
}
