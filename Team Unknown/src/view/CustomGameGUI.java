package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CustomGameGUI extends JFrame {

	
	private CustomGamePanel customGamePanel;
	private List<Point> customMap;
	private BufferedImage mapImage;
	
	
	public CustomGameGUI(int mapSelected){
		setTitle("Tower Defense");
		setSize(750, 800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		customGamePanel = new CustomGamePanel(this);
		customGamePanel.addMouseListener(new mouseListener());
		
		customMap = new LinkedList<Point>();
		
		JButton button = new JButton();
		button.setText("Save Map");
		
		ActionListener buttonListener = new ButtonListener();
		button.addActionListener(buttonListener);
		
		add(customGamePanel);
		add(button, BorderLayout.SOUTH);
		setVisible(true);
		repaint();
	}
	
	public List<Point> getCustomMap(){
		return customMap;
	}
	
	public BufferedImage getMapImage(){
		return mapImage;
	}

	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			// NEW MAP START THING
			System.out.println("adasdasd");;
		}
	}
	private class mouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Point p = new Point(e.getX() / 15, e.getY() / 15);
			if(e.getX() / 15 <= 49 && e.getX() / 15 >= 0 && e.getY() / 15 <= 49 && e.getY() / 15 >= 0){
				customMap.add(p);
				repaint();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
}