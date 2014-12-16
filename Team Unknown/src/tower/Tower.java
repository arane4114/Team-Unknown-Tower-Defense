package tower;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import model.Map;
import enemy.Enemy;
import enemy.Enemy1;
import enemy.Enemy2;

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
	private final double enemy1Multiplier;
	private final double enemy2Multiplier;
	private final double enemy3Multiplier;
	private int damageMultiplier;
	protected int level;
	private List<Point> pointsInRange;
	protected int upgradeCost;
	
	public int getlevel(){
		return level;
	}
	
	public int getDamageMultiplier(){
		return damageMultiplier;
	}
	
	public int getUpgradeCost(){
		return this.upgradeCost;
	}

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
	
	public void pause(){
		this.timer.stop();
	}
	
	public void upPause(){
		this.timer.start();
	}

	public void levelUp() {
		this.level++;
		this.damageMultiplier *= 2;
		this.upgradeCost += upgradeCost / 5;
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

	private boolean isInRange(Point p) {
		return Math.abs(p.distance(location)) <= range;
	}
	
	
	public List<Point> getPointsInRange(){
		return this.pointsInRange;
	}
	/*
	 * Sets up a basic attack algorithm. Can be modified if the tower requires
	 * it (the random tower will do so).
	 */
	protected void attackEnemy() {
		if (currentTarget instanceof Enemy1) {
			currentTarget.doDamage(damageAmount * enemy1Multiplier
					* damageMultiplier);
		} else if (currentTarget instanceof Enemy2) {
			currentTarget.doDamage(damageAmount * enemy2Multiplier
					* damageMultiplier);
		} else {
			currentTarget.doDamage(damageAmount * enemy3Multiplier
					* damageMultiplier);
		}
	}

	protected abstract void selectEnemyFromList(List<Enemy> listOfEnemies);

	private class TowerTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fireTurret();
		}

	}
}
