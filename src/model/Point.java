package model;

public class Point {
	
	private double[][] s;
	private int x;
	private int y;
	
	public Point(double[][] s, int x, int y) {
		this.s = s;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double[][] getS() {
		return s;
	}

	public void setS(double[][] s) {
		this.s = s;
	}

	
}
