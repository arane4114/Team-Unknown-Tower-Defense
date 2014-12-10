package enemy;

import map.*;

public class Enemy2 extends Enemy{

	public Enemy2(int multi, Map m) {
		super(100 * multi, m);
	}

	@Override
	public void doDamage(int damage) {
		health -= damage;
		System.out.println("Enemy health: "+health);
	}

}
