package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel{

	private BufferedImage mainMenuImage;
	
	public MainMenuPanel(){
		
		this.setPreferredSize(new Dimension(750, 750));
		
		try {
			mainMenuImage = ImageIO.read(new File("Pictures" + File.separator
					+ "MainMenu.jpg"));
		} catch (IOException e) {
			System.out.println("Could Not Print Image");
		}
		
		repaint();
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(mainMenuImage, 0, 0, null);
	        g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
	        g.setColor(Color.WHITE);
	        g.drawString("Unknown", 125, 100);
	        g.drawString("Tower Defense", 20, 200);
	        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
	        g.drawString("Level 1", 475, 300);
	        g.drawString("Level 2", 475, 370);
	        g.drawString("Level 3", 475, 440);
	        g.drawString("2-Player", 475, 510);
	        g.drawString("Exit Game", 475, 580);
	        
	}	
}
