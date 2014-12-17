package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import network.PointColorObject;

public class MiniMapPanel extends JPanel {

	
	private final int DELTA_X = 5;
	private final int DELTA_Y = 5;
	private final int X_BASE = 0;
	private final int Y_BASE = 0;
	
	
	private BufferedImage mapImage;
	
	private List<PointColorObject> pointColorList; 
	
	public  MiniMapPanel (){
		this.setPreferredSize(new Dimension(250, 250));
		pointColorList = null;
	}
	
	public void setBufferedImage(BufferedImage mapImage){
		this.mapImage = mapImage;
	}
	
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