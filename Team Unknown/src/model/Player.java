package model;

import java.io.Serializable;

/**
 * Player object holds information about the players statistics.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class Player implements Serializable {

	private int health;
	private int money;
	private int points;
	private Map map;

	private final int WIN_POINTS = 25;

	/**
	 * {@link Map} that the player is on.
	 * 
	 * @param map
	 *            {@link Map} object.
	 */
	public Player(Map map) {
		this.health = 10;
		this.money = 10;
		this.points = 0;
		this.map = map;
	}

	/**
	 * Getter for the players current health.
	 * 
	 * @return Health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Getter for the players current financial standings.
	 * 
	 * @return Money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Can the player spent an amount.
	 * 
	 * @param amount Amount of money the player wants to spend.
	 * @return If the player has enough money to buy.
	 */
	public boolean canBuy(int amount) {
		return money >= amount;
	}

	/**
	 * Deduct amount from students current money.
	 * 
	 * @param amount
	 *            Amount to deduct.
	 */
	public void buy(int amount) {
		this.money -= amount;
		map.forceUpdate();
	}

	/**
	 * Amount earned by player. Add amount to players money.
	 * 
	 * @param amount
	 *            Amount to add to money.
	 */
	public void earn(int amount) {
		this.money += amount;
		map.forceUpdate();
	}

	/**
	 * Damage given to player from an enemy.
	 * 
	 * @param damage
	 *            Damage to give to player.
	 */
	public void damage(int damage) {
		this.health -= damage;
		map.forceUpdate();
	}

	/**
	 * Getter for current points a player has.
	 * 
	 * @return Points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Add points to players points.
	 * 
	 * @param amount
	 *            Amount of points to add.
	 */
	public void addPoints(int amount) {
		this.points += amount;
	}

	/**
	 * Points needed till player wins.
	 * 
	 * @return Points needed
	 */
	public int getPointsToWin() {
		return WIN_POINTS - points;
	}

	/**
	 * Is the player a winner.
	 * 
	 * @return won boolean
	 */
	public boolean win() {
		return points >= WIN_POINTS;
	}
}
