package tower;

import java.awt.Point;
import java.util.List;

import map.*;
import enemy.*;

/**
 * This tower is tower with an API for testing general tower functions. It can
 * be setup in debug mode.
 *
 * @author Abhishek
 *
 */
public class Tower_Type_0 extends Tower {

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

	public String toString() {
		return "This is the standard\n tower. \n Damage to all enemies: "
				+ this.damageAmount + "\n Shots per second: " + 1000
				/ this.fireInterval + "\n Current level: " + this.level
				+ "\n Upgrade cost: " + this.upgradeCost;
	}
}
