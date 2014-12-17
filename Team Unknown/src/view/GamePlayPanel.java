package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Map;
import network.PointColorObject;
import network.TowerClient;
import tower.Tower_Type_0;
import tower.Tower_Type_1;
import tower.Tower_Type_2;
import tower.Tower_Type_3;
import tower.Tower_Type_4;
import enemy.Enemy1;
import enemy.Enemy2;
import enemy.Enemy3;

/**
 * This is the gameplay panel that shows the current state of the map.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class GamePlayPanel extends JPanel implements Observer {

	private static final long serialVersionUID = -2788781914134666681L;

	private Map map;
	private BufferedImage mapImage;
	private TowerDefenseGUI towerDefeseGUI;
	private List<PointColorObject> pointColorList;
	private TowerClient client;

	private final int DELTA_X = 15;
	private final int DELTA_Y = 15;
	private final int X_BASE = 0;
	private final int Y_BASE = 0;

	private List<Point> pointsInRange;
	
	public BufferedImage getMapImage(){
		return mapImage;
	}
	
	/**
	 * Creates a gameplay panel and will attempt to load the relevant assets.
	 * @param mapSelected The map to be played on.
	 * @param towerDefeseGUI A link to for end of game events.
	 */
	public GamePlayPanel(int mapSelected, TowerDefenseGUI towerDefeseGUI, TowerClient client) {
		this.setPreferredSize(new Dimension(750, 750));
		this.towerDefeseGUI = towerDefeseGUI;
		this.client = client;
		if (mapSelected == 2) {
			try {
				mapImage = ImageIO.read(new File("Pictures" + File.separator
						+ "Background2.jpg"));
			} catch (IOException e) {
				System.out.println("Could Not Load Background2.jpg");
			}
		} else if (mapSelected == 3) {
			try {
				mapImage = ImageIO.read(new File("Pictures" + File.separator
						+ "Background3.jpg"));
			} catch (IOException e) {
				System.out.println("Could Not Load Background3.jpg");
			}
		} else {
			try {
				mapImage = ImageIO.read(new File("Pictures" + File.separator
						+ "Background1.png"));
			} catch (IOException e) {
				System.out.println("Could Not Load Background.png");
			}
		}

	}
	
	/**
	 * Sets the points in range of a tower.
	 * @param pointsInRange A list of points where a tower can shoot, null if no tower is to be shown.
	 */
	public void setPointsInRange(List<Point> pointsInRange) {
		this.pointsInRange = pointsInRange;
	}

	@Override
	public void update(Observable o, Object unsed) {
		Map map = (Map) o;
		this.map = map;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mapImage, 0, 0, 750, 750, null);
		
		pointColorList = new LinkedList<PointColorObject>();
		
		for (Point p : this.map.getLeftPath()) {
			if (map.isEnemy(p)) {
				if (map.getListOfEnemies(p).size() > 1) {
					g.setColor(Color.BLACK);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				} else if (map.getListOfEnemies(p).get(0) instanceof Enemy1) {
					g.setColor(Color.YELLOW);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				} else if (map.getListOfEnemies(p).get(0) instanceof Enemy2) {
					g.setColor(Color.GREEN);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				} else if (map.getListOfEnemies(p).get(0) instanceof Enemy3) {
					g.setColor(Color.BLUE);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				}
			} else {
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
				pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
			}
		}

		for (Point p : this.map.getMiddlePath()) {
			if (map.isEnemy(p)) {
				if (map.getListOfEnemies(p).size() > 1) {
					g.setColor(Color.BLACK);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				} else if (map.getListOfEnemies(p).get(0) instanceof Enemy1) {
					g.setColor(Color.YELLOW);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				} else if (map.getListOfEnemies(p).get(0) instanceof Enemy2) {
					g.setColor(Color.GREEN);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				} else if (map.getListOfEnemies(p).get(0) instanceof Enemy3) {
					g.setColor(Color.BLUE);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				}
			} else {
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
				pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
			}
		}

		for (Point p : this.map.getRightPath()) {
			if (map.isEnemy(p)) {
				if (map.getListOfEnemies(p).size() > 0) {
					g.setColor(Color.BLACK);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				} else if (map.getListOfEnemies(p).get(0) instanceof Enemy1) {
					g.setColor(Color.YELLOW);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				} else if (map.getListOfEnemies(p).get(0) instanceof Enemy2) {
					g.setColor(Color.GREEN);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				} else if (map.getListOfEnemies(p).get(0) instanceof Enemy3) {
					g.setColor(Color.BLUE);
					g.fillRect(((p.x * DELTA_X) + X_BASE),
							((p.y * DELTA_Y) + Y_BASE), 15, 15);
					pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
				}
			} else {
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
				pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
			}
		}

		for (Point p : this.map.getTowers()) {
			if (map.getTower(p) instanceof Tower_Type_0) {
				g.setColor(Color.red);
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
				pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
			} else if (map.getTower(p) instanceof Tower_Type_1) {
				g.setColor(Color.yellow);
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
				pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
			} else if (map.getTower(p) instanceof Tower_Type_2) {
				g.setColor(Color.green);
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
				pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
			} else if (map.getTower(p) instanceof Tower_Type_3) {
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
				pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
			} else if (map.getTower(p) instanceof Tower_Type_4) {
				g.setColor(Color.black);
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
				pointColorList.add(new PointColorObject(new Point(p), g.getColor()));
			}
		}

		if (this.pointsInRange != null) {
			for (Point p : this.pointsInRange) {
				g.setColor(new Color(0, 0, 0, 177));
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
		}

		g.setColor(Color.WHITE);
		g.fillRect(((map.getGhostTower().x * DELTA_X) + X_BASE),
				((map.getGhostTower().y * DELTA_Y) + Y_BASE), 15, 15);

		if (map.getPlayer().getPointsToWin() <= 0) {
			towerDefeseGUI.visibleFalse();
			JOptionPane.showMessageDialog(new JFrame(), "Winner!!!", "Winner",
					JOptionPane.PLAIN_MESSAGE);
			towerDefeseGUI.mainMenuVisible();
		} else if (map.getPlayer().getHealth() <= 0) {
			towerDefeseGUI.visibleFalse();
			JOptionPane.showMessageDialog(new JFrame(), "Game Over",
					"Game Over", JOptionPane.PLAIN_MESSAGE);
			towerDefeseGUI.mainMenuVisible();
		}
		
		if(client != null){
			client.sendMiniMapUpdate(pointColorList);
		}

	}

}
