package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Map;

public class PlayerInfoPanel extends JPanel implements Observer{

	private Map map;
	
	public PlayerInfoPanel(){
		
		this.setPreferredSize(new Dimension(250, 375));
		
	}
	
	@Override
	public void update(Observable o, Object unsed) {
		Map map = (Map) o;
		this.map = map;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(0, 0, 250, 375);
		g.setColor(Color.WHITE);
		g.drawString("Health : " + map.getPlayer().getHealth(), 20, 30);
		g.drawString("Money : "  + map.getPlayer().getMoney(), 20, 50);
		g.drawString("Points : " + map.getPlayer().getPoints(), 20, 70);
		g.drawString("Points Needed : " + map.getPlayer().getPointsToWin(), 20, 90);
	}
}


