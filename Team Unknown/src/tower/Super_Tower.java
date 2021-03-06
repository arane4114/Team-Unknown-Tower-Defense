package tower;

import java.awt.Point;
import java.util.List;

import model.Map;
import enemy.Enemy;

/**
 * This tower is a rather special tower. It will either kill an enemy or restore
 * a damaged enemy to full health.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class Super_Tower extends Tower {

	public Super_Tower(int range, int fireInterval, Map map, Point location) {
		super(range, fireInterval, map, location, 0, 0, 0, 0, 20);
	}

	/**
	 * Will either kill an enemy or restore it to full health.
	 */
	@Override
	protected void attackEnemy() {
		int randNum = (int) (1 + Math.random() * 2);
		if (randNum == 1) {
			this.currentTarget.kill();
		} else {
			this.currentTarget.restoreHealth();
		}
	}

	@Override
	protected void selectEnemyFromList(List<Enemy> listOfEnemies) {
		int randomValue = (int) Math.random() * listOfEnemies.size();
		currentTarget = listOfEnemies.get(randomValue);
	}

	public void levelUp() {
		this.fireInterval /= 2;
		this.timer.setDelay(fireInterval);
	}

	/**
	 * Provides a description of the current tower and its stats.
	 */
	public String toString() {
		return "This is the Super\n tower." + "\n 50% to kill an enemy"
				+ "\n 50% chance to restore it \n to full health"
				+ "\n Shots per second: " + 1000 / this.fireInterval
				+ "\n Upgrades double fire rate" + "\n Use with cation!";
	}

}
