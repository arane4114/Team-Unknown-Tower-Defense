package model;

public class Armored extends Enemy{

	public Armored(int multi, Map m) {
		super(100 * multi, m);
	}

	@Override
	public void doDamage(int damage) {
		health -= damage/10;
	}

}
