package model;

public class Cell {
	private boolean path;
	
	public Cell() {
		this.path = false;
	}
	
	public void cellPath( boolean path){
		this.path = path;
	}
	
	public boolean getPath(){
		return path;
	}
}
