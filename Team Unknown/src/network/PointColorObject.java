package network;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public class PointColorObject implements Serializable {
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
