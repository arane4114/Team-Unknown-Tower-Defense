package tower;

import java.awt.Point;
import java.util.List;

import map.Map;
import enemy.Enemy;
import enemy.Enemy1;


public class Tower_Type_1 extends Tower{

	public Tower_Type_1(int range, int fireInterval, Map map,
			Point location, int damageAmount) {
		super(range, fireInterval, map, location, damageAmount, 2.0, 1.0, 0.5, 10);
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
	
	public String toString(){
		return "";
	}

}
