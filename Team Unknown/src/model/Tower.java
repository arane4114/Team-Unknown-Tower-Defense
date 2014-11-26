package model;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

public abstract class Tower {
	protected int range;
	protected int fireInterval;
	protected Timer timer;
	protected Map map;
	protected boolean isTargeting;
	protected Point location;
	protected Enemy currentTarget;
	protected final int damageAmount;

	protected Tower(int range, int fireInterval, Map map, Point location,
			int damageAmount) {
		this.range = range;
		this.fireInterval = fireInterval;
		this.map = map;
		this.location = location;
		this.timer = new Timer(this.fireInterval, new TowerTimer());
		this.currentTarget = null;
		this.damageAmount = damageAmount;
		timer.start();
	}

	protected Tower(int range, int fireInterval, Map map, Point location,
			int damageAmount, boolean debug) {
		this.range = range;
		this.fireInterval = fireInterval;
		this.map = map;
		this.location = location;
		this.timer = new Timer(this.fireInterval, new TowerTimer());
		this.currentTarget = null;
		this.damageAmount = damageAmount;
		if (!debug) {
			timer.start();
		}
	}

	private void fireTurret() {
		selectTarget();
		if (currentTarget != null) {
			attackEnemy();
		}
	}

	private void selectTarget() {
		if (currentTarget != null) {
			Point targetLocation = currentTarget.getCurrent();
			if (!currentTarget.isDead() && isInRange(targetLocation)) {
				return;
			} else {
				System.out.println("Target released");
				currentTarget = null;
				System.out.println(currentTarget);
			}
		}

		/*
		 * Check in a counter clockwise loop starting at the 6 o' clock
		 * position. Will check for enemies 1 spot radially outwards in all
		 * directions. Then 2... until max range or enemy is found. Requires an
		 * isInBounds and an enemies at location. Assumes enemies at location
		 * sends a list of all enemies in location. If multiple enemies are
		 * found will attack the weakest enemy;
		 */
		for (int i = 1; i <= range; i++) {
			Point searchLocation;
			// down
			searchLocation = new Point(location.x, location.y + i);
			if (map.isValid(searchLocation) && map.hasEnemy(searchLocation)) {
				List<Enemy> enemyList = map.getListOfEnemies(searchLocation);
				selectEnemyFromList(enemyList);
				return;
			}
			// down right
			searchLocation = new Point(location.x + i, location.y + i);
			if (map.isValid(searchLocation) && map.hasEnemy(searchLocation)) {
				List<Enemy> enemyList = map.getListOfEnemies(searchLocation);
				selectEnemyFromList(enemyList);
				return;
			}
			// right
			searchLocation = new Point(location.x + 1, location.y);
			if (map.isValid(searchLocation) && map.hasEnemy(searchLocation)) {
				List<Enemy> enemyList = map.getListOfEnemies(searchLocation);
				selectEnemyFromList(enemyList);
				return;
			}
			// up right
			searchLocation = new Point(location.x + 1, location.y - 1);
			if (map.isValid(searchLocation) && map.hasEnemy(searchLocation)) {
				List<Enemy> enemyList = map.getListOfEnemies(searchLocation);
				selectEnemyFromList(enemyList);
				return;
			}
			// up
			searchLocation = new Point(location.x, location.y - 1);
			if (map.isValid(searchLocation) && map.hasEnemy(searchLocation)) {
				List<Enemy> enemyList = map.getListOfEnemies(searchLocation);
				selectEnemyFromList(enemyList);
				return;
			}
			// up left
			searchLocation = new Point(location.x - 1, location.y - 1);
			if (map.isValid(searchLocation) && map.hasEnemy(searchLocation)) {
				List<Enemy> enemyList = map.getListOfEnemies(searchLocation);
				selectEnemyFromList(enemyList);
				return;
			}
			// left
			searchLocation = new Point(location.x - 1, location.y);
			if (map.isValid(searchLocation) && map.hasEnemy(searchLocation)) {
				List<Enemy> enemyList = map.getListOfEnemies(searchLocation);
				selectEnemyFromList(enemyList);
				return;
			}
			// down left
			searchLocation = new Point(location.x - 1, location.y + 1);
			if (map.isValid(searchLocation) && map.hasEnemy(searchLocation)) {
				List<Enemy> enemyList = map.getListOfEnemies(searchLocation);
				selectEnemyFromList(enemyList);
				return;
			}
		}

	}

	private boolean isInRange(Point p) {
		System.out.println("Distance " + p.distance(location));
		System.out.println(Math.abs(p.distance(location)) <= range);
		return Math.abs(p.distance(location)) <= range;
	}

	/*
	 * Allows specific turrets a way to target enemies.
	 */
	protected abstract void attackEnemy();

	protected abstract void selectEnemyFromList(List<Enemy> listOfEnemies);

	private class TowerTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fireTurret();
		}

	}
}
