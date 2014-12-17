package tower;

import java.awt.Point;
import java.util.List;

import model.Map;
import enemy.Enemy;

/**
 * This is the basic low cost tower in the game. It does a flat amount of damage
 * to all {@link Enemy} types.
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class Tower_Type_0 extends Tower {
	
	/**
	 * Public constructor for this tower.
	 * @param range The range it should attack.
	 * @param fireInterval Its rate of fire.
	 * @param map A link back to the {@link Map} object that it is on to query it for information.
	 * @param location Its location on the {@link Map}
	 * @param damageAmount The base damage of this tower
	 */
	public Tower_Type_0(int range, int fireInterval, Map map, Point location,
			int damageAmount) {
		super(range, fireInterval, map, location, damageAmount, 1.0, 1.0, 1.0,
				5);
	}

	@Override
	protected void selectEnemyFromList(List<Enemy> listOfEnemies) {
		int randomValue = (int) Math.random() * listOfEnemies.size();
		currentTarget = listOfEnemies.get(randomValue);
	}
	
	/**
	 * Provides a description of the current tower and its stats.
	 */
	public String toString() {
		return "This is the standard\n tower. \n Damage to all enemies: "
				+ this.damageAmount * this.damageMultiplier
				+ "\n Shots per second: " + 1000 / this.fireInterval
				+ "\n Current level: " + this.level + "\n Upgrade cost: "
				+ this.upgradeCost + "\n Upgrades doubles damage!";
	}
}
