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
public class TestTower extends Tower {

	public TestTower(int range, int fireInterval, Map map, Point location,
			int damageAmount) {
		super(range, fireInterval, map, location, damageAmount);
		System.out.println("Tower placed at:" + location);
	}

	@Override
	protected void attackEnemy() {
		System.out.println("Atack enemy called");
		currentTarget.doDamage(damageAmount);
	}

	@Override
	protected void selectEnemyFromList(List<Enemy> listOfEnemies) {
		int randomValue = (int) Math.random() * listOfEnemies.size();
		currentTarget = listOfEnemies.get(randomValue);
		System.out.println("Target selected at " + currentTarget.getCurrent());
	}
}
