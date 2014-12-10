package tower;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import map.Map;
import enemy.Enemy;

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

	protected Tower(int range, int fireInterval, Map map, Point location,
			int damageAmount) {
		this.range = range;
		this.fireInterval = fireInterval;
		this.map = map;
		this.location = location;
		this.timer = new Timer(this.fireInterval, new TowerTimer());
		this.currentTarget = null;
		this.damageAmount = damageAmount;
		this.validTargetPoints = new LinkedList<Point>();
		for (int i = 1; i <= range; i++) {
			Point searchLocation;
			// down
			searchLocation = new Point(location.x, location.y + i);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// down right
			searchLocation = new Point(location.x + i, location.y + i);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// right
			searchLocation = new Point(location.x + i, location.y);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// up right
			searchLocation = new Point(location.x + i, location.y - i);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// up
			searchLocation = new Point(location.x, location.y - i);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// up left
			searchLocation = new Point(location.x - i, location.y - i);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// left
			searchLocation = new Point(location.x - i, location.y);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
			// down left
			searchLocation = new Point(location.x - i, location.y + i);
			if (map.isValid(searchLocation) && map.isPath(searchLocation)) {
				this.validTargetPoints.add(searchLocation);
			}
		}
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
				System.out.println("Current target " + currentTarget);
				return;
			} else {
				System.out.println("Target released");
				currentTarget = null;
				System.out.println("Current target " + currentTarget);
			}
		}
		
		for(Point searchLocation: validTargetPoints){
			List<Enemy> enemiesAtLocation = map.getListOfEnemies(searchLocation);
			if(!enemiesAtLocation.isEmpty()){
				selectEnemyFromList(enemiesAtLocation);
				break;
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