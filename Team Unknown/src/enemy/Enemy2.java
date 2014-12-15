package enemy;

import map.*;

public class Enemy2 extends Enemy{

	public Enemy2(int multi, Map m) {
		super(100 * multi, m);
	}

	public void doDamage(double damage) {
		health -= damage;
	}

}
