package enemy;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Map;

/**
 * Enemy Spawner creates random new enemies
 * to spawn at the beginning of the path.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class EnemySpawner {

	private Map map;
	private Timer timer;
	private Timer timerTwo;
	private int spawnInterval = 15000;
	private int enemyInterval = 150;
	private int numberOfEnemies;
	private int count;
	private Enemy enemyToMake;
	
	public EnemySpawner(Map map){
		this.map = map;
		this.numberOfEnemies = 1;
		this.timer = new Timer(this.spawnInterval, new SpawnTimer());
		this.timerTwo = new Timer(this.enemyInterval, new EmenyTimer());
		timer.start();
	}
	
	/**
	 * Stops timer.
	 */
	public void timerStop(){
		timer.stop();
	}
	
	/**
	 * Private inner class for the waves of enemies.
	 * @author Bryce Hammond
	 */
	private class SpawnTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			count = 0;
			timerTwo.start();
		}
	}
	
	/**
	 * Stops timers for pause.
	 */
	public void pause(){
		timer.stop();
		timerTwo.stop();
	}
	
	/**
	 * Resumes timers.
	 */
	public void resume(){
		timer.start();
		timerTwo.start();
	}
	
	/**
	 * Private inner class for enemies in a wave.
	 * @author Bryce Hammond
	 */
	private class EmenyTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(count < numberOfEnemies){
				int random = (int) (Math.random() * 3);
				switch(random){
				case 0:
					enemyToMake = new Stone_Enemy(1,map);
					break;
				case 1:
					enemyToMake = new Fire_Enemy(2,map);
					break;
				case 2:
					enemyToMake = new Water_Enemy(3,map);
					break;
				}
				count++;
			}else{
				numberOfEnemies = numberOfEnemies * 2;
				timer.start();
				timerTwo.stop();
			}
		}
	}
}