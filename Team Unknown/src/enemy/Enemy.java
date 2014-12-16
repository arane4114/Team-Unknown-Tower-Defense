package enemy;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import model.Map;

public abstract class Enemy {
	
	protected int health, points, i;
	//private final static Image sprite;
	//Will need to set sprites for each enemy
	protected Point current;
	protected Map map;
	protected ArrayList<Point> path;
	protected Timer timer;
	protected int walkInterval = 100; 	// Not sure what the interval needs to be, adding 100 as a placeholder
	protected boolean alive;
	protected int baseHealth;
	
	public Enemy(int h, Map m) {
		health = h;
		points = health/10;
		i = 0;
		map = m;
		this.baseHealth = health;
		int random = (int) (Math.random() * 3);
		
		switch(random){
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
		map.addEnemy(current, this); //Added by Bryce
		this.timer = new Timer(this.walkInterval, new EnemyTimer());
		this.alive = true;
		timer.start();
	}

	abstract public void doDamage(double damage);	
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
	
	public boolean getAlive(){
		return alive;
	}

	public void moveToNext(){
		i++;
		if(health <= 0){
			map.getPlayer().addPoints(1);
			map.getPlayer().earn(2);
			map.removeEnemy(current, this);
			this.alive = false;
			timer.stop();
		}else if(i < path.size()){
			map.removeEnemy(current, this);
			current = path.get(i);
			map.addEnemy(current, this);
		}else{
			map.getPlayer().damage(1);
			map.removeEnemy(current, this);
			this.alive = false;
			timer.stop();
		}
	}

	public boolean isDead(){
		return health <= 0;
	}
	
	public void kill(){
		this.health = 0;
	}
	
	public void restoreHealth(){
		this.health = this.baseHealth;
	}

	private class EnemyTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			moveToNext();
			map.forceUpdate();
		}
	}

}
