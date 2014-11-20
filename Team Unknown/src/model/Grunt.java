package model;

public class Grunt extends Enemy{

	public Grunt(int multi, Cell c) { //Supply a multiplier to effect the unit's health from the base amount
		super(100 * multi, c); 
	}

	@Override
	public void doDamage(int damage) {
		health -= damage;
	}

}
