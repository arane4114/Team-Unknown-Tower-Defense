package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import map.*;

public class GamePlayPanel extends JPanel implements Observer{

	private static final long serialVersionUID = -2788781914134666681L;

	private Map map;
	private BufferedImage mapImage;
	
	private final int DELTA_X = 15;
	private final int DELTA_Y = 15;
	private final int X_BASE = 0;
	private final int Y_BASE = 0;

	public GamePlayPanel(int mapSelected){
		this.setPreferredSize(new Dimension(750, 750));
		
		if(mapSelected == 2){
			try {
				mapImage = ImageIO.read(new File("Pictures" + File.separator
						+ "Background2.jpg"));
			} catch (IOException e) {
				System.out.println("Could Not Load Background2.jpg");
			}
		}else if(mapSelected == 3){
			try {
				mapImage = ImageIO.read(new File("Pictures" + File.separator
						+ "Background3.jpg"));
			} catch (IOException e) {
				System.out.println("Could Not Load Background3.jpg");
			}
		}else{
			try {
				mapImage = ImageIO.read(new File("Pictures" + File.separator
						+ "Background1.png"));
			} catch (IOException e) {
				System.out.println("Could Not Load Background.png");
			}
		}
		
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

		for (Point p : this.map.getLeftPath()) {
			if(map.isEnemy(p)){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
		}
		
		for (Point p : this.map.getMiddlePath()) {
			if(map.isEnemy(p)){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
		}
		
		for (Point p : this.map.getRightPath()) {
			if(map.isEnemy(p)){
				g.setColor(Color.blue);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}else{
				g.setColor(Color.orange);
				g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
		}
		
		g.setColor(Color.red);
		for (Point p : this.map.getTowers()) {
			g.fillRect(((p.x * DELTA_X) + X_BASE),((p.y * DELTA_Y) + Y_BASE), 15, 15);
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(((map.getGhostTower().x * DELTA_X) + X_BASE),((map.getGhostTower().y * DELTA_Y) + Y_BASE), 15, 15);
				
		if(map.getPlayer().getPointsToWin() <= 0){
//			JOptionPane.showMessageDialog(new JFrame(),
//				    "Winner!!!",
//				    "Winner",
//				    JOptionPane.PLAIN_MESSAGE);
		}else if(map.getPlayer().getHealth() <= 0){
//			JOptionPane.showMessageDialog(new JFrame(),
//				    "Game Over",
//				    "Game Over",
//				    JOptionPane.PLAIN_MESSAGE);
		}

	}
	
}
