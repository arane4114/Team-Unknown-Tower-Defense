package tower;

import java.awt.Point;
import java.util.List;

import map.Map;
import enemy.Enemy;
import enemy.Enemy2;

public class Tower_Type_2 extends Tower {

	protected Tower_Type_2(int range, int fireInterval, Map map,
			Point location, int damageAmount) {
		super(range, fireInterval, map, location, damageAmount, 0.5,
				2.0, 1.0);
	}

	@Override
	protected void selectEnemyFromList(List<Enemy> listOfEnemies) {
		for(Enemy e: listOfEnemies){
			if(e instanceof Enemy2){
				currentTarget = e;
				return;
			}
		}
		int randomValue = (int) Math.random() * listOfEnemies.size();
		currentTarget = listOfEnemies.get(randomValue);
	}

}
