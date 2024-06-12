package model;

public class Boundary {
	private int xmin, xmax, ymin, ymax;
	
	public Boundary(int xmin, int ymin, int xmax,  int ymax) {
		this.xmin=xmin;
		this.ymin=ymin;
		this.xmax=xmax;
		this.ymax=ymax;
	}
	
	public int getxMin() {
		return xmin;
	}
	
	public int getyMin() {
		return ymin;
	}
	
	public int getxMax() {
		return xmax;
	}
	
	
	public int getyMax() {
		return ymax;
	}
	
	public boolean inBoundary(int x, int y) {
		return (x >= xmin && x <= xmax && y >= ymin && ymax >= ymax);
	}
}
