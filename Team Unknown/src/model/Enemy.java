package model;

public abstract class Enemy {

	protected int health, points;
	//private final static Image sprite;
	//Will need to set sprites for each enemy
	
	public Enemy(int h) {
		health = h;
		points = health/10;
	}
	
	abstract public void doDamage(int damage);	
	//Tower sends this command to an enemy to deal damage
	//Making it abstract so we can change how much damage each enemy takes
	// to account for armor or something
	
	public int getPoints(){
		return points; //Preliminary number of points given when enemy is killed
	}

	public int getHealth(){
		return health;
	}

}
