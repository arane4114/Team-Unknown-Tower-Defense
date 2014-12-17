package tower;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import model.Map;
import enemy.Enemy;
import enemy.Stone_Enemy;
import enemy.Fire_Enemy;

/**
 * Tower objects are placed on the {@link Map}. They shoot at {@link Enemy}
 * objects. This is an abstract class.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public abstract class Tower {
	protected int range;
	protected int fireInterval;
	protected Timer timer;
	protected Map map;
	protected boolean isTargeting;
	protected Point location;
	protected Enemy currentTarget;
	protected final int damageAmount;
	protected List<Point> validTargetPoints;
	protected final double enemy1Multiplier;
	protected final double enemy2Multiplier;
	protected final double enemy3Multiplier;
	protected int damageMultiplier;
	protected int level;
	private List<Point> pointsInRange;
	protected int upgradeCost;

	protected Tower(int range, int fireInterval, Map map, Point location,
			int damageAmount, double enemy1Multiplier, double enemy2Multiplier,
			double enemy3Multiplier, int upgradeCost) {
		this.range = range;
		this.fireInterval = fireInterval;
		this.map = map;
		this.location = location;
		this.timer = new Timer(this.fireInterval, new TowerTimer());
		this.currentTarget = null;
		this.damageAmount = damageAmount;
		this.validTargetPoints = new LinkedList<Point>();
		this.enemy1Multiplier = enemy1Multiplier;
		this.enemy2Multiplier = enemy2Multiplier;
		this.enemy3Multiplier = enemy3Multiplier;
		this.damageMultiplier = 1;
		this.level = 1;
		this.upgradeCost = upgradeCost;
		this.pointsInRange = new LinkedList<Point>();
		for (int i = 1; i <= range; i++) {
			Point searchLocation;
			// down
			searchLocation = new Point(location.x, location.y + i);
			this.pointsInRange.add(searchLocation);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// down right
			searchLocation = new Point(location.x + i, location.y + i);
			this.pointsInRange.add(searchLocation);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// right
			searchLocation = new Point(location.x + i, location.y);
			this.pointsInRange.add(searchLocation);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// up right
			searchLocation = new Point(location.x + i, location.y - i);
			this.pointsInRange.add(searchLocation);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// up
			searchLocation = new Point(location.x, location.y - i);
			this.pointsInRange.add(searchLocation);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// up left
			searchLocation = new Point(location.x - i, location.y - i);
			this.pointsInRange.add(searchLocation);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// left
			searchLocation = new Point(location.x - i, location.y);
			this.pointsInRange.add(searchLocation);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// down left
			searchLocation = new Point(location.x - i, location.y + i);
			this.pointsInRange.add(searchLocation);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
		}
		timer.start();
	}

	/**
	 * Gets the cost to upgrade the tower.
	 * 
	 * @return The cost to upgrade the tower.
	 */
	public int getUpgradeCost() {
		return this.upgradeCost;
	}

	/**
	 * Stops the fire timer and allows the game to pause.
	 */
	public void pause() {
		this.timer.stop();
	}

	/**
	 * Restarts the fire timer at the emd of a pause.
	 */
	public void resume() {
		this.timer.start();
	}

	/**
	 * Upgrades the towers damage multiplier.
	 */
	public void levelUp() {
		this.level++;
		this.damageMultiplier *= 2;
		this.upgradeCost *= 2;
	}

	/**
	 * Called by the fire timer every fireinterval miliseconds.
	 */
	private void fireTurret() {
		selectTarget();
		if (currentTarget != null) {
			attackEnemy();
		}
	}

	/**
	 * Creates a list of targets that are in spots the tower can reach. Sends
	 * that list to each individual tower to select an enemy.
	 */
	private void selectTarget() {
		if (currentTarget != null) {
			Point targetLocation = currentTarget.getCurrent();
			if (!currentTarget.isDead() && isInRange(targetLocation)) {
				return;
			} else {
				currentTarget = null;
			}
		}

		for (Point searchLocation : validTargetPoints) {
			List<Enemy> enemiesAtLocation = map
					.getListOfEnemies(searchLocation);
			if (!enemiesAtLocation.isEmpty()) {
				selectEnemyFromList(enemiesAtLocation);
				break;
			}
		}

	}
	
	/**
	 * Checks if a point is within range of the tower.
	 * @param p The point to be checked
	 * @return True if in range, false otherwise.
	 */
	private boolean isInRange(Point p) {
		return Math.abs(p.distance(location)) <= range;
	}

	public List<Point> getPointsInRange() {
		return this.pointsInRange;
	}

	/**
	 * This is the attack algorithm used by most towers. It is overrided by tower 4.
	 */
	protected void attackEnemy() {
		if (currentTarget instanceof Stone_Enemy) {
			currentTarget.doDamage(damageAmount * enemy1Multiplier
					* damageMultiplier);
		} else if (currentTarget instanceof Fire_Enemy) {
			currentTarget.doDamage(damageAmount * enemy2Multiplier
					* damageMultiplier);
		} else {
			currentTarget.doDamage(damageAmount * enemy3Multiplier
					* damageMultiplier);
		}
	}
	
	/**
	 * Allows a tower to choose its target from a list of enemies in range.
	 * @param listOfEnemies A list of all enemies in range.
	 */
	protected abstract void selectEnemyFromList(List<Enemy> listOfEnemies);
	
	/**
	 * Private inner class for the fire tower. 
	 * @author Abhishek
	 *
	 */
	private class TowerTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fireTurret();
		}

	}
}
