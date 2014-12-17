package enemy;

import model.Map;
import view.TowerDefenseGUI;

/**
 * This is an {@link enemy} that can be created.
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class Stone_Enemy extends Enemy{

	public Stone_Enemy(int multi, Map m) { //Supply a multiplier to effect the unit's health from the base amount
		super(100 * multi, m); 
	}

	@Override
	public void doDamage(double damage) {
		health -= damage;
	}

}
