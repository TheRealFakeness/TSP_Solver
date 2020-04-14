package model;

public class Node {
	
	//Attributes
	
	private int id;
	private double xCoord;
	private double yCoord;
	private double angle;
	private boolean onRoute;
	
	// Constructor
	
	public Node(int id, double xCoord, double yCoord) {
		this.id = id;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.onRoute = false;
		this.angle = 0;
	}
	
	
	// Setters && Getters
	
	public int getId() {
		return id;
	}

	public double getxCoord() {
		return xCoord;
	}

	public double getyCoord() {
		return yCoord;
	}

	public boolean isOnRoute() {
		return onRoute;
	}
	
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return angle;
	}
	
	
	
}
