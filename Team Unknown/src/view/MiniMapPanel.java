package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import tower.Tower_Type_0;
import tower.Tower_Type_1;
import tower.Tower_Type_2;
import tower.Tower_Type_3;
import tower.Tower_Type_4;
import enemy.Enemy1;
import enemy.Enemy2;
import enemy.Enemy3;
import model.MiniMap;

public class MiniMapPanel extends JPanel implements Observer{
	private MiniMap map;
	private final int DELTA_X = 5;
	private final int DELTA_Y = 5;
	private final int X_BASE = 0;
	private final int Y_BASE = 0;
	
	@Override
	public void update(Observable o, Object arg) {
		this.map = (MiniMap) o;
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
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
			}else if(map.getTower(p) instanceof Tower_Type_4){
				g.setColor(Color.black);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
		}
	}

}
