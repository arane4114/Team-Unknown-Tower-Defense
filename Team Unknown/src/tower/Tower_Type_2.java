package tower;

import java.awt.Point;
import java.util.List;

import model.Map;
import enemy.Enemy;
import enemy.Enemy2;

/**
 * This tower is better suited to do damgae to {@link Enemy2}.
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class Tower_Type_2 extends Tower {
	
	/**
	 * Public constructor for this tower.
	 * @param range The range it should attack.
	 * @param fireInterval Its rate of fire.
	 * @param map A link back to the {@link Map} object that it is on to query it for information.
	 * @param location Its location on the {@link Map}
	 * @param damageAmount The base damage of this tower
	 */
	public Tower_Type_2(int range, int fireInterval, Map map, Point location,
			int damageAmount) {
		super(range, fireInterval, map, location, damageAmount, 0.5, 2.0, 1.0,
				10);
	}

	@Override
	protected void selectEnemyFromList(List<Enemy> listOfEnemies) {
		for (Enemy e : listOfEnemies) {
			if (e instanceof Enemy2) {
				currentTarget = e;
				return;
			}
		}
		int randomValue = (int) Math.random() * listOfEnemies.size();
		currentTarget = listOfEnemies.get(randomValue);
	}

	/**
	 * Provides a description of the current tower and its stats.
	 */
	public String toString() {
		return "This is the third\n tower. \n Damage to enemy 1: "
				+ this.damageAmount * this.enemy1Multiplier
				* this.damageMultiplier + "\n Damage to enemy 2: "
				+ this.damageAmount * this.enemy2Multiplier
				* this.damageMultiplier + "\n Damage to enemy 3: "
				+ this.damageAmount * this.enemy3Multiplier
				* this.damageMultiplier + "\n Shots per second: " + 1000
				/ this.fireInterval + "\n Current level: " + this.level
				+ "\n Upgrade cost: " + this.upgradeCost
				+ "\n Upgrades doubles damage!";
	}
}
