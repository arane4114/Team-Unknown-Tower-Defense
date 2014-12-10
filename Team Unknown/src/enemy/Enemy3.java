package enemy;

import map.*;

public class Enemy3 extends Enemy{

	public Enemy3(int multi, Map m) {
		super(100 * multi, m);
	}

	public void doDamage(double damage) {
		health -= damage;
		System.out.println("Enemy health: "+health);
	}

}
