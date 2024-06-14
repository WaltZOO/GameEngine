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
		return (x >= xmin && x < xmax && y >= ymin && y < ymax);
	}
	
	 public boolean intersect(int x, int y, int radius) {
		 
	        int closestX = Math.max(getxMin(), Math.min(x, getxMax()));
	        int closestY = Math.max(getyMin(), Math.min(y, getyMax()));

	        int distX = x - closestX;
	        int distY = y - closestY;

	        int dist = distX*distX + distY*distY;

	        return radius*radius >= dist;
	    } 
}
