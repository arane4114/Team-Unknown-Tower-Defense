package tower;

import java.awt.Point;
import java.util.List;

import enemy.Enemy;
import enemy.Enemy1;
import enemy.Enemy2;
import map.Map;


public class Tower_Type_1 extends Tower{

	protected Tower_Type_1(int range, int fireInterval, Map map,
			Point location, int damageAmount) {
		super(range, fireInterval, map, location, damageAmount);
	}

	@Override
	protected void attackEnemy() {
		if(currentTarget instanceof Enemy1){
			currentTarget.doDamage(damageAmount * 2);
		}else if(currentTarget instanceof Enemy2){
			currentTarget.doDamage(damageAmount);
		}else{
			currentTarget.doDamage(damageAmount / 2);
		}
	}

	@Override
	protected void selectEnemyFromList(List<Enemy> listOfEnemies) {
		for(Enemy e: listOfEnemies){
			if(e instanceof Enemy1){
				currentTarget = e;
				return;
			}
		}
		int randomValue = (int) Math.random() * listOfEnemies.size();
		currentTarget = listOfEnemies.get(randomValue);
	}

}
