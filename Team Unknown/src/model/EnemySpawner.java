package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class EnemySpawner {

	private Map map;
	private Timer timer;
	private Timer timerTwo;
	private int spawnInterval = 15000;
	private int enemyInterval = 150;
	private int numberOfEnemies;
	private int count;
	
	public EnemySpawner(Map map){
		this.map = map;
		this.numberOfEnemies = 1;
		this.timer = new Timer(this.spawnInterval, new SpawnTimer());
		this.timerTwo = new Timer(this.enemyInterval, new EmenyTimer());
		timer.start();
	}
	
	public void timerStop(){
		timer.stop();
	}
	
	private class SpawnTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			count = 0;
			timerTwo.start();
		}
	}
	
	private class EmenyTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(count < numberOfEnemies){
				Enemy g = new Grunt(1,map);
				count++;
			}else{
				numberOfEnemies++;
				timer.start();
				timerTwo.stop();
			}
		}
	}
}