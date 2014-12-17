package enemy;

import model.Map;
import view.TowerDefenseGUI;

public class Water_Enemy extends Enemy{

	public Water_Enemy(int multi, Map m) {
		super(100 * multi, m);
	}

	public void doDamage(double damage) {
		health -= damage;
	}

}
