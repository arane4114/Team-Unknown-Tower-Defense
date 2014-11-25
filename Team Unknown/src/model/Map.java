package model;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

public class Map extends Observable {
	
	private Cell[][] map;
	private ArrayList<Point> leftPath;
	private ArrayList<Point> middlePath;
	private ArrayList<Point> rightPath;
	private ArrayList<Point> towers;
	
	private boolean test = false;
	
	public Map() {
		map = new Cell[50][50];
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				map[i][j] = new Cell();
			}
		}
		test = true;
		setPath();
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
	
	public void setTower(Point p, Tower t){
		map[p.y][p.x].setTower(t);
		towers.add(p);
	}
	
	public boolean isValid(Point p){
		if(p.x < 50 && p.y < 50 && p.x >= 0 && p.y >= 0){
				return true;
		}    
	return false;
	}
	
	public boolean isEnemy(Point p) {
		return map[p.y][p.x].isTower();
	}
	
	public ArrayList<Enemy> getListOfEnemies(Point p){
		return map[p.y][p.x].getEnemies();
	}
	
	public void mapToString(){
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if(map[i][j].getPath()){
					System.out.print("[P]");
				}else{
					System.out.print("[X]");
				}
			}
			System.out.println();
		}
	}
}