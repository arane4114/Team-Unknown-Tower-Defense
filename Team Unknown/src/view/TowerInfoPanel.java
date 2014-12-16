package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import tower.Tower;

public class TowerInfoPanel extends JPanel {

	private Tower currentTower;

	public void setTower(Tower tower) {
		this.currentTower = tower;
	}

	public TowerInfoPanel() {
		this.setPreferredSize(new Dimension(250, 375));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 250, 375);
	}
}
