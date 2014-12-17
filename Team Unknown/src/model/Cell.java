package model;

import java.util.ArrayList;

import tower.*;
import enemy.*;

/**
 * Cell objects are placed in the {@link Map}. They link  enemies {@link Enemy}
 * and towers {@link Tower} to the game board.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
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
	
	/**
	 * Returns true or false the cell has an enemy on it.
	 * @return If the cell has an enemy present on it.
	 */
	public boolean isEnemy(){
		if(enemies.isEmpty()){
			return false;
		}
	return true;
	}
	
	/**
	 * Returns true or false if cell has an tower on it.
	 * @return If the cell has an tower present on it.
	 */
	public boolean isTower(){
		return isTower;
	}
	
	/**
	 * Sets cell to be a path.
	 * @param path Path to be set.
	 */
	public void cellPath( boolean path){
		this.path = path;
	}
	
	/**
	 * Returns true or false if the cell is a path.
	 * @return If the cell is a path.
	 */
	public boolean isPath(){
		return path;
	}
	
	/**
	 * Sets tower on Cell.
	 * @param tower Tower to be set.
	 */
	public void setTower(Tower tower){
		this.isTower = true;
		this.tower = tower;
	}
	
	/**
	 * Adds enemy to cell, can have more than one enemy on cell.
	 * @param enemy Enemy to be added.
	 */
	public void addEnemy(Enemy enemy){
		this.enemies.add(enemy);
	}
	
	/**
	 * Removes enemy from cell.
	 * @param enemy Enemy to be removed.
	 */
	public void removeEnemy(Enemy enemy){
		this.enemies.remove(enemy);
	}
	
	/**
	 * Returns tower at cell.
	 * @return Tower 
	 */
	public Tower getTower(){
		return tower;
	}
	
	/**
	 * Returns list of enemies on cell.
	 * @return ArrayList<Enemy>
	 */
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
}
