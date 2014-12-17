package enemy;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;

import javax.swing.Timer;

import model.Map;
import network.TowerClient;

import tower.Super_Tower;

/**
 * Enemy objects are placed on the {@link Map}. They travel through a path. This
 * is an abstract class and can be written to disk.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public abstract class Enemy implements Serializable {

	protected int health, points, i;
	protected Point current;
	protected Map map;
	protected List<Point> path;
	transient protected Timer timer;
	protected int walkInterval = 100;

	protected boolean alive;
	protected int baseHealth;

	/**
	 * This is the base enemy spawner. Take the health and map its on.
	 * 
	 * @param h
	 *            base health
	 * @param m
	 *            map this enemy will be on
	 */
	public Enemy(int h, Map m) {
		health = h;
		points = health / 10;
		i = 0;
		map = m;
		this.baseHealth = health;
		int random = (int) (Math.random() * 3);

		switch (random) {
		case 0:
			path = map.getLeftPath();
			break;
		case 1:
			path = map.getMiddlePath();
			break;
		case 2:
			path = map.getRightPath();
			break;
		}

		current = path.get(i);
		map.addEnemy(current, this);
		this.timer = new Timer(this.walkInterval, new EnemyTimer());
		this.alive = true;
		timer.start();
	}

	/**
	 * Does damage on an enemy. Is abstract as subclasses may want to handle
	 * damage differently.
	 * 
	 * @param damage
	 *            Amount of damage taken
	 */
	abstract public void doDamage(double damage);

	/**
	 * Gets the points of an enemy.
	 * 
	 * @return The points of an enemy.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Gets the health of an enemy.
	 * 
	 * @return The health of an enemy.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Gets the current point location of an enemy.
	 * 
	 * @return The point location of an enemy.
	 */
	public Point getCurrent() {
		return current;
	}

	/**
	 * Gets the alive status of an enemy.
	 * 
	 * @return The alive status of an enemy.
	 */
	public boolean getAlive() {
		return alive;
	}

	/**
	 * Moves enemy to next point in its path. Also checks if enemy is alive or
	 * has mad it to the end of its path.
	 */
	public void moveToNext() {
		i++;
		if (health <= 0) {
			map.getPlayer().addPoints(1);
			map.getPlayer().earn(2);
			map.removeEnemy(current, this);
			this.alive = false;
			timer.stop();
		} else if (i < path.size()) {
			map.removeEnemy(current, this);
			current = path.get(i);
			map.addEnemy(current, this);
		} else {
			map.getPlayer().damage(1);
			TowerClient client = map.getTowerDefenseGUI().getTowerClient();
			if (client != null) {
				client.hasBeenHit();
			}
			map.removeEnemy(current, this);
			this.alive = false;
			timer.stop();
		}
	}

	/**
	 * Gets the if the enemy is dead.
	 * 
	 * @return If the enemy is dead.
	 */
	public boolean isDead() {
		return health <= 0;
	}

	/**
	 * Kills enemy. Used by the {@link Super_Tower}.
	 */
	public void kill() {
		this.health = 0;
	}

	/**
	 * Restores enemy's health. Used by the {@link Super_Tower}.
	 */
	public void restoreHealth() {
		this.health = this.baseHealth;
	}

	/**
	 * Stops timers for pause.
	 */
	public void pause() {
		this.timer.stop();
	}

	/**
	 * Restarts timers for resume game.
	 */
	public void resume() {
		this.timer.restart();
	}

	/**
	 * Recreates timers after being loaded from disk.
	 */
	public void wasLoadedFromDisk() {
		this.timer = new Timer(this.walkInterval, new EnemyTimer());
	}

	/**
	 * Enemy timer action listener for movement of enemies.
	 * 
	 * @author Bryce Hammond
	 *
	 */
	private class EnemyTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			moveToNext();
			map.forceUpdate();
		}
	}

}
