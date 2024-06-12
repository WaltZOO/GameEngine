package ai;

public class Direction {
	public static final int H = 0; // Here
	public static final int F = 1; // Forward
	public static final int B = 2; // Backward
	public static final int L = 3; // Left
	public static final int R = 4; // Right
	public static final int N = 5; // North
	public static final int S = 6; // South
	public static final int E = 7; // East
	public static final int W = 8; // West
	public static final int SW = 9; // South West
	public static final int NW = 10; // North West
	public static final int SE = 11; // South East
	public static final int NE = 12; // North East

	public Direction() {
	}
}

class Category {
	public static final int V = 0;	// Void
	public static final int O = 1;	// Obstacle
	public static final int T = 3;	// Team
	public static final int A = 4;	// Autre
	public static final int D= 5;	
	public static final int E= 6;
	public static final int W = 7;
	public static final int M = 8;
	public static final int ME = 9;
}
