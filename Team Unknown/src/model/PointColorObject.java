package model;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

/**
 * PointColorObject object is used to store point and color information to make
 * sending a mini map over the network easier. This was done to avoid sending
 * objects with timers.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class PointColorObject implements Serializable{
	private Point point;
	private Color color;

	/**
	 * Creates a point color object.
	 * 
	 * @param point
	 *            Location information.
	 * @param color
	 *            Color information.
	 */
	public PointColorObject(Point point, Color color) {
		this.point = point;
		this.color = color;

	}

	/**
	 * Getter for Point within PointColorObject.
	 * 
	 * @return Point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * Getter for Color within PointColorObject.
	 * 
	 * @return Color
	 */
	public Color getColor() {
		return color;
	}
}