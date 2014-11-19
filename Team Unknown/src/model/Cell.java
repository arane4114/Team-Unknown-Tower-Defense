package model;

import java.awt.Point;
import java.util.ArrayList;

public class Cell {
	private boolean path;
	private ArrayList<Enemy> enemies;
	private Tower tower;
	
	
	public Cell() {
		this.path = false;
	}
	
	public void cellPath( boolean path){
		this.path = path;
	}
	
	public boolean getPath(){
		return path;
	}
	
	public void setTower(Tower tower){
		this.tower = tower;
	}
	
	public void setEnemy(Enemy enemy){
		this.enemies.add(enemy);
	}
	
	public void removeEnemy(Enemy enemy){
		this.enemies.remove(enemy);
	}
	
	public Tower getTower(){
		return tower;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
}
