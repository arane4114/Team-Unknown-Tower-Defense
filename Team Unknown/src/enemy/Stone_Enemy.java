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
public class Stone_Enemy extends Enemy {

	/**
	 * Creates a stone enemy
	 * 
	 * @param multi
	 *            health multiplier
	 * @param m
	 *            map
	 */
	public Stone_Enemy(int multi, Map m) {
		super(100 * multi, m);
	}

	@Override
	public void doDamage(double damage) {
		health -= damage;
	}

}
