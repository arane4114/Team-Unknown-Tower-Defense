package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import tower.*;
import model.*;
import enemy.*;

public class Map extends Observable {
	
	private Cell[][] map;
	private ArrayList<Point> leftPath;
	private ArrayList<Point> middlePath;
	private ArrayList<Point> rightPath;
	private ArrayList<Point> towers;
	private Point ghostTower;
	private Player player;
	private EnemySpawner enemySpawner;
	private boolean spawner;
	
	public Map() {
		map = new Cell[50][50];
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				map[i][j] = new Cell();
			}
		}
		setPath();
		this.towers = new ArrayList<Point>();
		this.ghostTower = new Point(-1, -1);
		this.player = new Player();
		this.spawner = false;
	}

	private void setPath() {
		this.leftPath = new ArrayList<Point>();
		this.middlePath = new ArrayList<Point>();
		this.rightPath = new ArrayList<Point>();
		
		for(int i = 0; i < 24; i++){
			leftPath.add(new Point(i,11));
			map[11][i].cellPath(true);
			if(i < 23){
				middlePath.add(new Point(i,12));
				map[12][i].cellPath(true);	
			}
			if(i < 22){
				rightPath.add(new Point(i,13));
				map[13][i].cellPath(true);	
			}
		}
		
		for(int i = 12; i < 39; i++){
			if(i < 37){
				leftPath.add(new Point(23,i));			
				map[i][23].cellPath(true);
			}
			if(i > 12 && i < 38){
				map[i][22].cellPath(true);
				middlePath.add(new Point(22,i));
			}
			if(i > 13){
				map[i][21].cellPath(true);
				rightPath.add(new Point(21,i));	
			}
		}
		
		for(int i = 21; i < 50; i++){
			if(i > 22){
				leftPath.add(new Point(i,37));			
				map[37][i].cellPath(true);
			}
			if(i > 21){
				map[38][i].cellPath(true);
				middlePath.add(new Point(i,38));
			}
			map[39][i].cellPath(true);
			rightPath.add(new Point(i,39));	
		}
	}

	public ArrayList<Point> getLeftPath(){
		return this.leftPath;
	}
	
	public ArrayList<Point> getMiddlePath(){
		return this.middlePath;
	}	
	
	public ArrayList<Point> getRightPath(){
		return this.rightPath;
	}
	
	public ArrayList<Point> getTowers(){
		return this.towers;
	}
	
	public boolean hasTowers(){
		if(towers.isEmpty()){
			return false;
		}
	return true;
	}
	
	public void forceUpdate(){
		this.setChanged();
		this.notifyObservers();
	}
	
	public void removeEnemy(Point p, Enemy e){
		map[p.y][p.x].removeEnemy(e);
	}
	
	public void addEnemy(Point p, Enemy e){
		map[p.y][p.x].addEnemy(e);
	}
	
	public void setTower(Point p){
		if(spawner == false){
			enemySpawner = new EnemySpawner(this);
			spawner = true;
		}
		Tower t = new TestTower(5, 10, this, p, 1);
		map[p.y][p.x].setTower(t);
		towers.add(p);
	}
	
	public boolean isValid(Point p){
		if(p.x < 50 && p.y < 50 && p.x >= 0 && p.y >= 0){
				return true;
		}    
	return false;
	}
	
	public boolean isPath(Point p){
		return map[p.y][p.x].isPath();
	}
	
	public boolean isTower(Point p){
		return map[p.y][p.x].isTower();
	}
	
	public boolean isEnemy(Point p) {
		return map[p.y][p.x].isEnemy();
	}
	
	public ArrayList<Enemy> getListOfEnemies(Point p){
		return map[p.y][p.x].getEnemies();
	}
	
	public void setGhostTower(Point p){
		ghostTower = p;
	}
	
	public Point getGhostTower(){
		return ghostTower;
	}

	public Player getPlayer(){
		return player;
	}
	
	public EnemySpawner getEnemySpawner(){
		return enemySpawner;
	}
	
	public void mapToString(){
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if(map[i][j].isPath()){
					System.out.print("[P]");
				}else{
					System.out.print("[X]");
				}
			}
			System.out.println();
		}
	}

	public boolean hasEnemy(Point p) {
		return !map[p.y][p.x].getEnemies().isEmpty();
	}
}