package view;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import network.PointColorObject;

/**
 * This is the minimap panel shows the current state of other players map.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class MiniMapPanel extends JPanel {

	
	private final int DELTA_X = 5;
	private final int DELTA_Y = 5;
	private final int X_BASE = 0;
	private final int Y_BASE = 0;
	
	
	private BufferedImage mapImage;
	
	private List<PointColorObject> pointColorList; 
	
	/**
	 * Creates a minimap panel and will attempt to load the relevant assets.
	 * @param gamePlayPanel For the panel to have access to mapImage.
	 */
	public  MiniMapPanel (){
		this.setPreferredSize(new Dimension(250, 250));
		pointColorList = null;
	}
	
	/**
	 * Sets mapImage for miniPanel to use.
	 * @param mapImage
	 */
	public void setBufferedImage(BufferedImage mapImage){
		this.mapImage = mapImage;
	}
	
	/**
	 * Sets an updated pointColorList for the minimap panel to draw.
	 * @param pointColorList List of PointColorObject to draw.
	 */
	public void setPointColorList(List<PointColorObject> pointColorList2){
		this.pointColorList = pointColorList2;
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(mapImage != null){
			g.drawImage(mapImage, 0, 0, 250, 250, null);
			if(pointColorList != null){
				g.drawImage(mapImage, 0, 0, 250, 250, null);
				for (PointColorObject o : pointColorList) {
					g.setColor(o.getColor());
					g.fillRect(((o.getPoint().x * DELTA_X) + X_BASE),((o.getPoint().y * DELTA_Y) + Y_BASE), 5, 5);
				}
			}
		}
	}
}