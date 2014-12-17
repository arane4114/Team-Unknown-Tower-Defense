package enemy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Map;
import view.TowerDefenseGUI;

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
	
	public void pause(){
		timer.stop();
		timerTwo.stop();
	}
	
	public void resume(){
		timer.start();
		timerTwo.start();
	}
	
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