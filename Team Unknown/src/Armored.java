
public class Armored extends Enemy{

	public Armored(int multi) {
		super(100 * multi);
	}

	@Override
	public void doDamage(int damage) {
		health -= damage/10;
	}

}
