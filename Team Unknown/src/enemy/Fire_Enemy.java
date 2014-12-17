package enemy;

import model.Map;
import view.TowerDefenseGUI;

/**
 * This is an {@link enemy} that can be created.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class Fire_Enemy extends Enemy {

	/**
	 * Creates a fire enemy
	 * 
	 * @param multi
	 *            health multiplier
	 * @param m
	 *            map
	 */
	public Fire_Enemy(int multi, Map m) {
		super(100 * multi, m);
	}

	public void doDamage(double damage) {
		health -= damage;
	}

}
