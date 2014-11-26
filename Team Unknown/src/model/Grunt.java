package model;

public class Grunt extends Enemy{

	public Grunt(int multi, Map m) { //Supply a multiplier to effect the unit's health from the base amount
		super(100 * multi, m); 
	}

	@Override
	public void doDamage(int damage) {
		health -= damage;
		System.out.println("Grunt health: "+health);
	}

}
