package model;

public class Player {

	private int health;
	private int money;
	private int points;
	private Map map;
	
	private final int WIN_POINTS = 25;
	
	public Player(Map map){
		this.health = 10;
		this.money = 10;
		this.points = 0;
		this.map = map;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getMoney(){
		return money;
	}
	
	public boolean canBuy(int amount){
		return money >= amount;
	}
	
	public void buy(int amount){
		this.money -= amount;
		map.forceUpdate();
	}
	
	public void earn(int amount){
		this.money += amount;
		map.forceUpdate();
	}
	
	public void damage(int damage){
		this.health -= damage;
		map.forceUpdate();
	}
	
	public int getPoints(){
		return points;
	}
	
	public void addPoints(int amount){
		this.points += amount;
	}
	
	public int getPointsToWin(){
		return WIN_POINTS - points;
	}
	
	public boolean win(){
		return points >= WIN_POINTS;
	}
}
