package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;



public class CustomGamePanel extends JPanel {

	private final int DELTA_X = 15;
	private final int DELTA_Y = 15;
	private final int X_BASE = 0;
	private final int Y_BASE = 0;
	
	private CustomGameGUI customGameGUI;
	
	public CustomGamePanel(CustomGameGUI customGameGUI){
		this.setPreferredSize(new Dimension(750, 750));
		this.customGameGUI = customGameGUI;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawImage(customGameGUI.getMapImage(), 0, 0, 750, 750, null);
		g.setColor(Color.ORANGE);
		if(customGameGUI.getCustomMap() != null){
			for (Point p : customGameGUI.getCustomMap()) {
				g.fillRect(((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), 15, 15);
			}
		}

	}
}
