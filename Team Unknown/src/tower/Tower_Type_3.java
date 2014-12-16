package tower;

import java.awt.Point;
import java.util.List;

import model.Map;
import enemy.Enemy;
import enemy.Enemy3;

public class Tower_Type_3 extends Tower{

	public Tower_Type_3(int range, int fireInterval, Map map,
			Point location, int damageAmount) {
		super(range, fireInterval, map, location, damageAmount, 1.0,
				0.5, 2.0, 10);
	}

	@Override
	protected void selectEnemyFromList(List<Enemy> listOfEnemies) {
		for(Enemy e: listOfEnemies){
			if(e instanceof Enemy3){
				currentTarget = e;
				return;
			}
		}
		int randomValue = (int) Math.random() * listOfEnemies.size();
		currentTarget = listOfEnemies.get(randomValue);
	}

}
