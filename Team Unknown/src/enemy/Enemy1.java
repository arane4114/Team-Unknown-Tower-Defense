package enemy;

import map.*;

public class Enemy1 extends Enemy{

	public Enemy1(int multi, Map m) { //Supply a multiplier to effect the unit's health from the base amount
		super(100 * multi, m); 
	}

	@Override
	public void doDamage(int damage) {
		health -= damage;
		System.out.println("Enemy health: "+health);
	}

}
