package network;

import java.awt.Color;
import java.awt.Point;

public class PointColorObject {
	private Point point;
	private Color color;
	
	public PointColorObject(Point point, Color color){
		this.point = point;
		this.color = color;
		
	}
	
	public Point getPoint(){
		return point;
	}
	
	public Color getColor(){
		return color;
	}
}
