package tower;

import java.awt.Point;
import java.util.List;

import model.Map;
import enemy.Enemy;

public class Tower_Type_4 extends Tower {

	public Tower_Type_4(int range, int fireInterval, Map map,
			Point location) {
		super(range, fireInterval, map, location, 0, 0,
				0, 0, 20);
	}

	@Override
	protected void attackEnemy(){
        int randNum = (int) (1 + Math.random()*2);
        if(randNum == 1){
        	this.currentTarget.kill();
        }else{
        	this.currentTarget.restoreHealth();
        }
	}
	
	@Override
	protected void selectEnemyFromList(List<Enemy> listOfEnemies) {
		int randomValue = (int) Math.random() * listOfEnemies.size();
		currentTarget = listOfEnemies.get(randomValue);
	}

}
