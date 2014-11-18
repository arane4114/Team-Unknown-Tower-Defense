package model;
import java.awt.Point;
import java.util.ArrayList;


public class Map {
	
	private Cell[][] map;
	private ArrayList<Point> leftPath;
	private ArrayList<Point> middlePath;
	private ArrayList<Point> rightPath;
	
	public Map() {
		map = new Cell[50][50];
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				map[i][j] = new Cell();
			}
		}
		setPath();
	}

	private void setPath() {
		this.leftPath = new ArrayList<Point>();
		this.middlePath = new ArrayList<Point>();
		this.rightPath = new ArrayList<Point>();
		
		for(int i = 0; i < 24; i++){
			leftPath.add(new Point(i,11));
			map[11][i].cellPath(true);
			if(i < 23){
				middlePath.add(new Point(i,12));
				map[12][i].cellPath(true);	
			}
			if(i < 22){
				rightPath.add(new Point(i,13));
				map[13][i].cellPath(true);	
			}
		}
		
		for(int i = 12; i < 39; i++){
			if(i < 37){
				leftPath.add(new Point(23,i));			
				map[i][23].cellPath(true);
			}
			if(i > 12 && i < 38){
				map[i][22].cellPath(true);
				middlePath.add(new Point(22,i));
			}
			if(i > 13){
				map[i][21].cellPath(true);
				rightPath.add(new Point(21,i));	
			}
		}
		
		for(int i = 21; i < 50; i++){
			if(i > 22){
				leftPath.add(new Point(i,37));			
				map[37][i].cellPath(true);
			}
			if(i > 21){
				map[38][i].cellPath(true);
				middlePath.add(new Point(i,38));
			}
			map[39][i].cellPath(true);
			rightPath.add(new Point(i,39));	
		}
	}

	public void mapToString(){
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if(map[i][j].getPath()){
					System.out.print("[P]");
				}else{
					System.out.print("[X]");
				}
			}
			System.out.println();
		}
	}
	
//	public static void main(String[] args) {
//		new Map();
//	}
}
