package model;

public class Armored extends Enemy{

	public Armored(int multi, Cell c) {
		super(100 * multi, c);
	}

	@Override
	public void doDamage(int damage) {
		health -= damage/10;
	}

}
