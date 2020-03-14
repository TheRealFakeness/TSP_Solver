package model;

public class Node {
	
	//Attributes
	
	private int id;
	private double xCoord;
	private double yCoord;
	private double[] angles;
	private boolean onRoute;
	
	// Constructor
	
	public Node(int id, double xCoord, double yCoord) {
		this.id = id;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.onRoute = false;
	}
	
	// Getters
	
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
	
	
	public  void setFirstAngle(double value) {
		angles[0] = value;
	}
	public void setSecondAngle(double value) {
		angles[1] = value;
	}
	
	
}
