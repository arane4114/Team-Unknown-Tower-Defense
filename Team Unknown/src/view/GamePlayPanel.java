package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import map.*;

public class GamePlayPanel extends JPanel implements Observer{

	private static final long serialVersionUID = -2788781914134666681L;

	private Map map;
	
	private final int DELTA_X = 10;
	private final int DELTA_Y = 10;
	private final int X_BASE = 0;
	private final int Y_BASE = 0;

	public GamePlayPanel(){
		
		this.setPreferredSize(new Dimension(500, 500));
		
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
		g.fillRect(X_BASE, Y_BASE, 500, 500);

		for (Point p : this.map.getLeftPath()) {
			if(map.isEnemy(p)){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}
		}
		
		for (Point p : this.map.getMiddlePath()) {
			if(map.isEnemy(p)){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}
		}
		
		for (Point p : this.map.getRightPath()) {
			if(map.isEnemy(p)){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}else{
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
			}
		}
		
		g.setColor(Color.red);
		for (Point p : this.map.getTowers()) {
			g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 10, 10);
		}
		
		g.setColor(Color.white);
		g.fillRect(((map.getGhostTower().x * DELTA_X) + X_BASE),((map.getGhostTower().y * DELTA_Y) + Y_BASE), 10, 10);
		
		g.drawString("                Health : " + map.getPlayer().getHealth(), 350, 30);
		g.drawString("                Money : "  + map.getPlayer().getMoney(), 350, 50);
		g.drawString("                Points : " + map.getPlayer().getPoints(), 350, 70);
		g.drawString("Points Needed : " + map.getPlayer().getPointsToWin(), 350, 90);
		g.drawString("Towers cost 5 money.", 350, 110);
		
		
		if(map.getPlayer().getPointsToWin() <= 0){
			g.setColor(Color.green);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g.drawString("WINNER", 150, 75);
			map.getEnemySpawner().timerStop();
		}else if(map.getPlayer().getHealth() <= 0){
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g.drawString("GAME OVER", 100, 75);
			map.getEnemySpawner().timerStop();
		}

	}
	
}
