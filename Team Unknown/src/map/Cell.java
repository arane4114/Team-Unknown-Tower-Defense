package map;

import java.util.ArrayList;

import tower.*;
import enemy.*;

public class Cell {
	private boolean path;
	private boolean isTower;
	private ArrayList<Enemy> enemies;
	private Tower tower;
	
	public Cell() {
		this.path = false;
		this.isTower = false;
		this.enemies = new ArrayList<Enemy>();
	}
	
	public boolean isEnemy(){
		if(enemies.isEmpty()){
			return false;
		}
	return true;
	}
	
	public boolean isTower(){
		return isTower;
	}
	
	public void cellPath( boolean path){
		this.path = path;
	}
	
	public boolean isPath(){
		return path;
	}
	
	public void setTower(Tower tower){
		this.isTower = true;
		this.tower = tower;
	}
	
	public void addEnemy(Enemy enemy){
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