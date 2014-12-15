package view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import enemy.Enemy1;
import enemy.Enemy2;
import enemy.Enemy3;
import tower.Tower_Type_0;
import tower.Tower_Type_1;
import tower.Tower_Type_2;
import tower.Tower_Type_3;
import map.*;

public class GamePlayPanel extends JPanel implements Observer{

	private static final long serialVersionUID = -2788781914134666681L;

	private Map map;
	private BufferedImage mapImage;
	private TowerDefenseGUI towerDefeseGUI;
	
	private final int DELTA_X = 15;
	private final int DELTA_Y = 15;
	private final int X_BASE = 0;
	private final int Y_BASE = 0;

	public GamePlayPanel(int mapSelected, TowerDefenseGUI towerDefeseGUI){
		this.setPreferredSize(new Dimension(750, 750));
		this.towerDefeseGUI = towerDefeseGUI;
		if(mapSelected == 2){
			try {
				mapImage = ImageIO.read(new File("Pictures" + File.separator
						+ "Background2.jpg"));
			} catch (IOException e) {
				System.out.println("Could Not Load Background2.jpg");
			}
		}else if(mapSelected == 3){
			try {
				mapImage = ImageIO.read(new File("Pictures" + File.separator
						+ "Background3.jpg"));
			} catch (IOException e) {
				System.out.println("Could Not Load Background3.jpg");
			}
		}else{
			try {
				mapImage = ImageIO.read(new File("Pictures" + File.separator
						+ "Background1.png"));
			} catch (IOException e) {
				System.out.println("Could Not Load Background.png");
			}
		}
		
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
		g.drawImage(mapImage, 0, 0, 750, 750, null);

		for (Point p : this.map.getLeftPath()) {
			if(map.isEnemy(p)){
				if(map.getListOfEnemies(p).size() > 1){
					g.setColor(Color.BLACK);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}else if(map.getListOfEnemies(p).get(0) instanceof Enemy1){
					g.setColor(Color.YELLOW);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}else if(map.getListOfEnemies(p).get(0) instanceof Enemy2){
					g.setColor(Color.GREEN);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}else if(map.getListOfEnemies(p).get(0) instanceof Enemy3){
					g.setColor(Color.BLUE);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
		}
		
		for (Point p : this.map.getMiddlePath()) {
			if(map.isEnemy(p)){
				if(map.getListOfEnemies(p).size() > 1){
					g.setColor(Color.BLACK);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}else if(map.getListOfEnemies(p).get(0) instanceof Enemy1){
					g.setColor(Color.YELLOW);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}else if(map.getListOfEnemies(p).get(0) instanceof Enemy2){
					g.setColor(Color.GREEN);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}else if(map.getListOfEnemies(p).get(0) instanceof Enemy3){
					g.setColor(Color.BLUE);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
		}
		
		for (Point p : this.map.getRightPath()) {
			if(map.isEnemy(p)){
				if(map.getListOfEnemies(p).size() > 0){
					g.setColor(Color.BLACK);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}else if(map.getListOfEnemies(p).get(0) instanceof Enemy1){
					g.setColor(Color.YELLOW);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}else if(map.getListOfEnemies(p).get(0) instanceof Enemy2){
					g.setColor(Color.GREEN);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}else if(map.getListOfEnemies(p).get(0) instanceof Enemy3){
					g.setColor(Color.BLUE);
					g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
				}
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
		}
		
		
		for (Point p : this.map.getTowers()) {
			if(map.getTower(p) instanceof Tower_Type_0){
				g.setColor(Color.red);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}else if(map.getTower(p) instanceof Tower_Type_1){
				g.setColor(Color.yellow	);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}else if(map.getTower(p) instanceof Tower_Type_2){
				g.setColor(Color.green);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}else if(map.getTower(p) instanceof Tower_Type_3){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
			
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(((map.getGhostTower().x * DELTA_X) + X_BASE),((map.getGhostTower().y * DELTA_Y) + Y_BASE), 15, 15);
				
		if(map.getPlayer().getPointsToWin() <= 0){
			towerDefeseGUI.visibleFalse();
			JOptionPane.showMessageDialog(new JFrame(),
		    "Winner!!!",
		    "Winner",
		    JOptionPane.PLAIN_MESSAGE);
			towerDefeseGUI.mainMenuVisible();
		}else if(map.getPlayer().getHealth() <= 0){
			towerDefeseGUI.visibleFalse();
			JOptionPane.showMessageDialog(new JFrame(),
		    "Game Over",
		    "Game Over",
		    JOptionPane.PLAIN_MESSAGE);
			towerDefeseGUI.mainMenuVisible();
		}

	}
	
}
