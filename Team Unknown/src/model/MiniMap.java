package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import enemy.Enemy;

public class MiniMap extends Observable{
	private Cell[][] map;
	private ArrayList<Point> leftPath;
	private ArrayList<Point> middlePath;
	private ArrayList<Point> rightPath;
	private ArrayList<Point> towers;
	
	public MiniMap(){
		map = new Cell[50][50];
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				map[i][j] = new Cell();
			}
		}	
	}
	
	public void receiveUpdates(Cell[][] map, ArrayList<Point> towers){
		this.towers = null;
		this.towers = towers;
	}
	
	public ArrayList<Point> getTowers(){
		return towers;
	}
	 
	public ArrayList<Point> getLeftPath(){
		return this.leftPath;
	}


	public ArrayList<Point> getRightPath(){
		return this.rightPath;
	}
	
	public ArrayList<Point> getMiddlePath(){
		return this.middlePath;
	}
	
	public ArrayList<Enemy> getListOfEnemies(Point p) {
		return map[p.y][p.x].getEnemies();
	}
}
