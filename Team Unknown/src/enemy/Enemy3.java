package enemy;

import model.Map;

public class Enemy3 extends Enemy{

	public Enemy3(int multi, Map m) {
		super(100 * multi, m);
	}

	public void doDamage(double damage) {
		health -= damage;
	}

}
