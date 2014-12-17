package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import tower.Fire_Tower;
import tower.Neutral_Tower;
import tower.Stone_Tower;
import tower.Super_Tower;
import tower.Tower;
import tower.Water_Tower;
import view.TowerDefenseGUI;
import enemy.Enemy;
import enemy.EnemySpawner;
import view.CustomGameGUI;

/**
 * This class is the main model class for this application. It stores the
 * current state of the application and is the start point for serialization.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class Map extends Observable implements Serializable {

	private Cell[][] map;
	private List<Point> leftPath;
	private List<Point> middlePath;
	private List<Point> rightPath;
	private List<Point> towers;
	private Point ghostTower;
	private Player player;
	private EnemySpawner enemySpawner;
	private boolean spawner;
	private int mapId;
	transient private TowerDefenseGUI gui;

	/**
	 * Creates a map and links it back to the GUI that is observing it.
	 * 
	 * @param gui
	 *            This is needed when {@link Enemy} objects need to call back to
	 *            the gui.
	 */
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

	/**
	 * Used to set the current map.
	 * 
	 * @param mapId
	 *            map number
	 */
	public void setMapId(int mapId) {
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

	/**
	 * Returns the map number
	 * 
	 * @return The map number
	 */
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

	/**
	 * Returns the list containing all points on the left path.
	 * 
	 * @return The list with the left path.
	 */
	public List<Point> getLeftPath() {
		return this.leftPath;
	}

	/**
	 * Returns the list containing all points on the middle path.
	 * 
	 * @return The list with the middle path.
	 */
	public List<Point> getMiddlePath() {
		return this.middlePath;
	}

	/**
	 * Returns the list containing all points on the right path.
	 * 
	 * @return The list with the right path.
	 */
	public List<Point> getRightPath() {
		return this.rightPath;
	}

	/**
	 * Returns a list of all points where towers are located.
	 * 
	 * @return A list of all tower locations.
	 */
	public List<Point> getTowers() {
		return this.towers;
	}

	/**
	 * Checks if any towers are currently on the map.
	 * 
	 * @return True if there is a tower present, false otherwise.
	 */
	public boolean hasTowers() {
		if (towers.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Used to force an update of the model and by extension the GUI.
	 */
	public void forceUpdate() {
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Removes an {@link Enemy} from a point
	 * 
	 * @param p
	 *            Location of the enemy
	 * @param e
	 *            {@link Enemy} to be removed
	 */
	public void removeEnemy(Point p, Enemy e) {
		map[p.y][p.x].removeEnemy(e);
	}

	/**
	 * Adds an {@link Enemy} from a point
	 * 
	 * @param p
	 *            Location of the enemy
	 * @param e
	 *            {@link Enemy} to be added
	 */
	public void addEnemy(Point p, Enemy e) {
		map[p.y][p.x].addEnemy(e);
	}

	/**
	 * Adds a {@link Tower} to a location. Also starts the {@link EnemySpawner}
	 * if it isnt started.
	 * 
	 * @param towerType
	 *            Type The type of {@link Tower} to be placed.
	 * @param p
	 *            The location to be placed.
	 */
	public void setTower(int towerType, Point p) {
		if (spawner == false) {
			enemySpawner = new EnemySpawner(this);
			spawner = true;
		}
		if (towerType == 1) {
			Tower t = new Neutral_Tower(6, 10, this, p, 4);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		} else if (towerType == 2) {
			Tower t = new Fire_Tower(6, 10, this, p, 5);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		} else if (towerType == 3) {
			Tower t = new Water_Tower(6, 10, this, p, 5);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		} else if (towerType == 4) {
			Tower t = new Stone_Tower(6, 10, this, p, 5);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		} else if (towerType == 5) {
			Tower t = new Super_Tower(10, 250, this, p);
			map[p.y][p.x].setTower(t);
			towers.add(p);
		}
	}

	/**
	 * Get a {@link Tower} at a location
	 * 
	 * @param p
	 *            Location
	 * @return The {@link Tower} at the location.
	 */
	public Tower getTower(Point p) {
		return map[p.y][p.x].getTower();
	}

	/**
	 * Checks if a point is in a valid location
	 * 
	 * @param p
	 *            Point to check
	 * @return True if the point is in a valid location on a map, false
	 *         otherwise
	 */
	public boolean isValid(Point p) {
		if (p.x < 50 && p.y < 50 && p.x >= 0 && p.y >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if a point is a path object.
	 * 
	 * @param p
	 *            Point to check.
	 * @return True if it is a path, false otherwise.
	 */
	public boolean isPath(Point p) {
		return map[p.y][p.x].isPath();
	}

	/**
	 * Checks if a point is a tower
	 * 
	 * @param p
	 *            Point to check
	 * @return True if it is a {@link Tower}, false otherwise.
	 */
	public boolean isTower(Point p) {
		return map[p.y][p.x].isTower();
	}

	/**
	 * Checks if a point is an {@link Enemy}.
	 * 
	 * @param p
	 *            Point to check
	 * @return True if it is an {@link Enemy}, false otherwise.
	 */
	public boolean isEnemy(Point p) {
		return map[p.y][p.x].isEnemy();
	}

	/**
	 * Returns a list of {@link Enemy} at a location.
	 * 
	 * @param p
	 *            Location
	 * @return List of {@link Enemy} at a location. May be empty, will not be
	 *         null;
	 */
	public List<Enemy> getListOfEnemies(Point p) {
		return map[p.y][p.x].getEnemies();
	}

	/**
	 * Sets the location of the current ghost tower.
	 * 
	 * @param p
	 *            Current location of the mouse
	 */
	public void setGhostTower(Point p) {
		ghostTower = p;
	}

	/**
	 * Gets the current ghost pointer location.
	 * 
	 * @return Ghost pointer location.
	 */
	public Point getGhostTower() {
		return ghostTower;
	}

	/**
	 * Gets the {@link Player} attached to the map
	 * 
	 * @return The {@link Player}
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the {@link EnemySpawner} associated with the map.
	 * 
	 * @return The {@link EnemySpawner} associated with the map.
	 */
	public EnemySpawner getEnemySpawner() {
		return enemySpawner;
	}

	/**
	 * Returns the GUI linked with this map.
	 * 
	 * @return The {@link TowerDefenseGUI} linked to this map.
	 */
	public TowerDefenseGUI getTowerDefenseGUI() {
		return this.gui;
	}

	/**
	 * Performs operations needed to restart the game after being loaded from
	 * the game.
	 * 
	 * @param gui
	 *            The new {@link TowerDefenseGUI} object to be linked to.
	 */
	public void wasLoadedFromDisk(TowerDefenseGUI gui) {
		this.gui = gui;
		for (Point p : towers) {
			this.getTower(p).wasLoadedFromDisk();
		}

		for (Point p : leftPath) {
			for (Enemy e : this.getListOfEnemies(p)) {
				e.wasLoadedFromDisk();
			}
		}

		for (Point p : middlePath) {
			for (Enemy e : this.getListOfEnemies(p)) {
				e.wasLoadedFromDisk();
			}
		}

		for (Point p : rightPath) {
			for (Enemy e : this.getListOfEnemies(p)) {
				e.wasLoadedFromDisk();
			}
		}

		this.enemySpawner.wasLoadedFromDisk();
	}

	/**
	 * Overrides the paths for a given map with custom points. Used by the
	 * {@link CustomGameGUI}
	 * 
	 * @param customMapPoints
	 *            Set of map points to replace the original points.
	 */
	public void overridePoints(List<Point> customMapPoints) {
		this.leftPath = customMapPoints;
		this.rightPath = customMapPoints;
		this.middlePath = customMapPoints;
	}
}
