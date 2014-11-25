package model;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.Timer;

public abstract class Enemy {
	
	protected int health, points, i;
	//private final static Image sprite;
	//Will need to set sprites for each enemy
	protected Point current;
	protected Map map;
	protected ArrayList<Point> path;
	protected Timer timer;
	protected int walkInterval = 100; 	// Not sure what the interval needs to be, adding 100 as a placeholder

	public Enemy(int h, Map m) {
		health = h;
		points = health/10;
		i = 0;
		map = m;
		path = map.getMiddlePath(); // Getting Middle Path to start with, we can randomize this if we need.
		current = path.get(i);
		map.addEnemy(current, this); //Added by Bryce
		this.timer = new Timer(this.walkInterval, new EnemyTimer());
		timer.start();
	}

	abstract public void doDamage(int damage);	
	// Tower sends this command to an enemy to deal damage
	// Making it abstract so we can change how much damage each enemy takes
	//  to account for armor or something
	
	public int getPoints(){
		return points; 					//Preliminary number of points given when enemy is killed
	}

	public int getHealth(){
		return health;
	}

	public Point getCurrent(){
		return current;
	}

	public void moveToNext(){
		map.removeEnemy(current, this); // Added by Bryce
		i++;
		current = path.get(i); 	//Walk to the next point in the list
		map.addEnemy(current, this); // Added by Bryce
	}

	public boolean isDead(){
		return health <= 0;
	}

	private class EnemyTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			moveToNext();
		}
	}

}
