package tower;

import java.awt.Point;
import java.util.List;

import map.Map;
import enemy.Enemy;

public class Tower_Type_4 extends Tower {

	public Tower_Type_4(int range, int fireInterval, Map map,
			Point location, int damageAmount, double enemy1Multiplier,
			double enemy2Multiplier, double enemy3Multiplier, int upgradeCost) {
		super(range, fireInterval, map, location, damageAmount, enemy1Multiplier,
				enemy2Multiplier, enemy3Multiplier, upgradeCost);
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
