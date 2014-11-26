package model;

public class Player {

	private int health;
	private int money;
	private int points;
	
	private final int WIN_POINTS = 25;
	
	public Player(){
		this.health = 10;
		this.money = 10;
		this.points = 0;
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
	}
	
	public void earn(int amount){
		this.money += amount;
	}
	
	public void damage(int damage){
		this.health -= damage;
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
