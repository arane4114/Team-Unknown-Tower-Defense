package model;

public class Grunt extends Enemy{

	public Grunt(int multi) { //Supply a multiplier to effect the unit's health from the base amount
		super(100 * multi); 
	}

	@Override
	public void doDamage(int damage) {
		health -= damage;
	}

}
