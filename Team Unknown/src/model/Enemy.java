package model;

public abstract class Enemy {

	protected int health, points;
	//private final static Image sprite;
	//Will need to set sprites for each enemy
	private Cell current;
	
	public Enemy(int h, Cell c) {
		health = h;
		points = health/10;
		current = c;
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
	
	public void setCell(Cell n){
		current = n;
	}
	
	public Cell getCell(){
		return current;
	}
	
	public void moveToNext(){
		//change current cell to next cell in map list.
		//can be done in Enemy OR in the Game itself.
	}

}
