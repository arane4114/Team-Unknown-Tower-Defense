package model;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public abstract class Tower {
	protected int range;
	protected int fireInterval;
	protected Timer timer;
	protected Map map;
	protected boolean isTargeting;
	protected Point location;
	protected Enemy currentTarget;
	protected final int damageAmount;


	public Tower(int range, int fireInterval, Map map, Point location,
			int damageAmount) {
		this.range = range;
		this.fireInterval = fireInterval;
		this.map = map;
		this.location = location;
		this.timer = new Timer(this.fireInterval, new TowerTimer());
		this.currentTarget = null;
		this.damageAmount = damageAmount;
		timer.start();
	}

	private void fireTurret() {
		selectTarget();
		if (currentTarget != null) {
			attackEnemy();
		}
	}

	private void selectTarget() {
		if (currentTarget != null) {
			/*
			 * if(currentTarget still exists on the map && it is in range){
			 * return; } else { currentTarget = null; }
			 */
		}

		/*
		 * Check in a counter clockwise loop starting at the 6 o' clock
		 * position. Will check for enemies 1 spot radially outwards in all
		 * directions. Then 2... until max range or enemy is found. Requires an
		 * isInBounds and an enemies at location. Assumes enemies at location
		 * sends a list of all enemies in location. If multiple enemies are
		 * found will attack the weakest enemy;
		 */
		for (int i = 1; i <= range; i++) {
			
		}

	}

	/*
	 * Allows specific turrets a way to target enemies.
	 */
	protected abstract void attackEnemy();

	private class TowerTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fireTurret();
		}

	}
}
