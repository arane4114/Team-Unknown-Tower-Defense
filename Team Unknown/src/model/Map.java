package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import tower.Tower;
import tower.Tower_Type_0;
import tower.Tower_Type_1;
import tower.Tower_Type_2;
import tower.Tower_Type_3;
import tower.Tower_Type_4;
import view.TowerDefenseGUI;
import enemy.Enemy;
import enemy.EnemySpawner;

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
	private int mapId;
	private TowerDefenseGUI gui;

	public Map(TowerDefenseGUI gui) {
		map = new Cell[50][50];
		this.gui = gui;
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				map[i][j] = new Cell();
			}
		}

		this.towers = new ArrayList<Point>();
		this.ghostTower = new Point(-1, -1);
		this.player = new Player(this);
		this.spawner = false;
	}
	
	public void setMapId(int mapId){
		this.mapId = mapId;
		if (mapId == 2) {
			setPathTwo();
		} else if (mapId == 3) {
			setPathThree();
		} else {
			setPathOne();
		}
		this.forceUpdate();
	}
	
	public int getMapNumber() {
		return mapId;
	}

	private void setPathOne() {
		this.leftPath = new ArrayList<Point>();
		this.middlePath = new ArrayList<Point>();
		this.rightPath = new ArrayList<Point>();

		for (int i = 0; i < 24; i++) {
			leftPath.add(new Point(i, 11));
			map[11][i].cellPath(true);
			if (i < 23) {
				middlePath.add(new Point(i, 12));
				map[12][i].cellPath(true);
			}
			if (i < 22) {
				rightPath.add(new Point(i, 13));
				map[13][i].cellPath(true);
			}
		}

		for (int i = 12; i < 39; i++) {
			if (i < 37) {
				leftPath.add(new Point(23, i));
				map[i][23].cellPath(true);
			}
			if (i > 12 && i < 38) {
				map[i][22].cellPath(true);
				middlePath.add(new Point(22, i));
			}
			if (i > 13) {
				map[i][21].cellPath(true);
				rightPath.add(new Point(21, i));
			}
		}

		for (int i = 21; i < 50; i++) {
			if (i > 22) {
				leftPath.add(new Point(i, 37));
				map[37][i].cellPath(true);
			}
			if (i > 21) {
				map[38][i].cellPath(true);
				middlePath.add(new Point(i, 38));
			}
			map[39][i].cellPath(true);
			rightPath.add(new Point(i, 39));
		}
	}

	private void setPathTwo() {
		this.leftPath = new ArrayList<Point>();
		this.middlePath = new ArrayList<Point>();
		this.rightPath = new ArrayList<Point>();

		for (int i = 0; i < 38; i++) {
			rightPath.add(new Point(5, i));
			map[i][5].cellPath(true);
			if (i < 37) {
				middlePath.add(new Point(6, i));
				map[i][6].cellPath(true);
			}
			if (i < 36) {
				leftPath.add(new Point(7, i));
				map[i][7].cellPath(true);
			}
		}

		for (int i = 5; i < 18; i++) {
			if (i > 6 && i < 16) {
				leftPath.add(new Point(i, 36));
				map[36][i].cellPath(true);
			}
			if (i > 5 && i < 17) {
				middlePath.add(new Point(i, 37));
				map[37][i].cellPath(true);
			}
			rightPath.add(new Point(i, 38));
			map[38][i].cellPath(true);
		}

		for (int i = 37; i > 9; i--) {
			if (i > 11) {
				rightPath.add(new Point(17, i));
				map[i][17].cellPath(true);
			}
			if (i < 37 && i > 10) {
				map[i][16].cellPath(true);
				middlePath.add(new Point(16, i));
			}
			if (i < 36) {
				map[i][15].cellPath(true);
				leftPath.add(new Point(15, i));
			}
		}

		for (int i = 16; i < 40; i++) {
			leftPath.add(new Point(i, 10));
			map[10][i].cellPath(true);
			if (i > 16 && i < 39) {
				middlePath.add(new Point(i, 11));
				map[11][i].cellPath(true);
			}
			if (i > 17 && i < 38) {
				rightPath.add(new Point(i, 12));
				map[12][i].cellPath(true);
			}
		}

		for (int i = 11; i < 50; i++) {
			leftPath.add(new Point(39, i));
			map[i][39].cellPath(true);
			if (i > 11) {
				map[i][38].cellPath(true);
				middlePath.add(new Point(38, i));
			}
			if (i > 12) {
				map[i][37].cellPath(true);
				rightPath.add(new Point(37, i));
			}
		}
	}

	private void setPathThree() {
		this.leftPath = new ArrayList<Point>();
		this.middlePath = new ArrayList<Point>();
		this.rightPath = new ArrayList<Point>();

		for (int i = 0; i < 24; i++) {
			leftPath.add(new Point(i, 10));
			map[10][i].cellPath(true);
			if (i < 22) {
				middlePath.add(new Point(i, 12));
				map[12][i].cellPath(true);
			}
			if (i < 20) {
				rightPath.add(new Point(i, 14));
				map[14][i].cellPath(true);
			}
		}

		for (int i = 10; i < 40; i++) {
			if (i < 37) {
				leftPath.add(new Point(24, i));
				map[i][24].cellPath(true);
			}
			if (i > 11 && i < 38) {
				map[i][22].cellPath(true);
				middlePath.add(new Point(22, i));
			}
			if (i > 13) {
				map[i][20].cellPath(true);
				rightPath.add(new Point(20, i));
			}
		}

		for (int i = 20; i < 50; i++) {
			if (i > 24) {
				leftPath.add(new Point(i, 36));
				map[36][i].cellPath(true);
			}
			if (i > 21) {
				map[38][i].cellPath(true);
				middlePath.add(new Point(i, 38));
			}
			map[40][i].cellPath(true);
			rightPath.add(new Point(i, 40));
		}
	}

	public ArrayList<Point> getLeftPath() {
		return this.leftPath;
	}

	public ArrayList<Point> getMiddlePath() {
		return this.middlePath;
	}

	public ArrayList<Point> getRightPath() {
		return this.rightPath;
	}

	public ArrayList<Point> getTowers() {
		return this.towers;
	}

	public boolean hasTowers() {
		if (towers.isEmpty()) {
			return false;
		}
		return true;
	}

	public void forceUpdate() {
		this.setChanged();
		this.notifyObservers();
	}

	public void removeEnemy(Point p, Enemy e) {
		map[p.y][p.x].removeEnemy(e);
	}

	public void addEnemy(Point p, Enemy e) {
		map[p.y][p.x].addEnemy(e);
	}

	public void setTower(int towerType, Point p) {
		if (spawner == false) {
			enemySpawner = new EnemySpawner(this);
			spawner = true;
		}
		if (towerType == 1) {
			Tower t = new Tower_Type_0(6, 10, this, p, 4);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		} else if (towerType == 2) {
			Tower t = new Tower_Type_1(6, 10, this, p, 5);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		} else if (towerType == 3) {
			Tower t = new Tower_Type_2(6, 10, this, p, 5);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		} else if (towerType == 4) {
			Tower t = new Tower_Type_3(6, 10, this, p, 5);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		} else if (towerType == 5) {
			Tower t = new Tower_Type_4(10, 1000, this, p);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		}
	}

	public Tower getTower(Point p) {
		return map[p.y][p.x].getTower();
	}

	public boolean isValid(Point p) {
		if (p.x < 50 && p.y < 50 && p.x >= 0 && p.y >= 0) {
			return true;
		}
		return false;
	}

	public boolean isPath(Point p) {
		return map[p.y][p.x].isPath();
	}

	public boolean isTower(Point p) {
		return map[p.y][p.x].isTower();
	}

	public boolean isEnemy(Point p) {
		return map[p.y][p.x].isEnemy();
	}

	public ArrayList<Enemy> getListOfEnemies(Point p) {
		return map[p.y][p.x].getEnemies();
	}

	public void setGhostTower(Point p) {
		ghostTower = p;
	}

	public Point getGhostTower() {
		return ghostTower;
	}

	public Player getPlayer() {
		return player;
	}

	public EnemySpawner getEnemySpawner() {
		return enemySpawner;
	}
	
	public TowerDefenseGUI getTowerDefenseGUI(){
		return this.gui;
	}

	public void mapToString() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if (map[i][j].isPath()) {
					System.out.print("[P]");
				} else {
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
