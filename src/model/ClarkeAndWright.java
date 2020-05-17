package model;

import java.util.ArrayList;
import java.util.Random;

public class ClarkeAndWright implements Solver {

	/*
	 * Symmetric matrix that represents the distance between nodes, where
	 * distMatrix[i][j] is the distance from the node i to the node j
	 */
	private double[][] distMatrix;

	/*
	 * List of all nodes in the current instance
	 */
	private Node[] nodes;

	/*
	 * The initial node of the tour
	 */
	private Node origin;

	/*
	 * Number of tours
	 */
	private int tourNums;

	/*
	 * Savings matrix of joining the first node of tour i with the first node of the
	 * tour j
	 */
	private Double[][] s1;

	/*
	 * Savings matrix of joining the first node of tour i with the last node of the
	 * tour j
	 */
	private Double[][] s2;

	/*
	 * Savings matrix of joining the last node of tour i with the first node of tour
	 * j
	 */
	private Double[][] s3;

	/*
	 * Savings matrix of joining the last node of tour i with the last node of tour
	 * j
	 */
	private Double[][] s4;

	ArrayList<ArrayList<Node>> tours;

	/*
	 * Creates an instance of the ClarkeAndWright class
	 */
	public ClarkeAndWright() {

	}

	@Override
	public Solution solve(Model model) {
		this.distMatrix = model.getDistMatrix();
		this.nodes = model.getNodes();
		this.origin = model.getOrigin();
		this.tourNums = nodes.length - 1;

		Node[] route = algorithm();

		return null;
	}

	private Node[] algorithm() {

		if (origin == null) {
			Random r = new Random();
			origin = nodes[r.nextInt(nodes.length)];
		}

		tours = new ArrayList<ArrayList<Node>>();

		for (int k = 0; k < nodes.length; k++) {

			if (nodes[k].equals(origin) == false) {
				ArrayList<Node> tour = new ArrayList<Node>();
				tour.add(nodes[k]);

				tours.add(tour);

			}

		}

		while (tourNums > 1) {
			
			double[][] s1 = calculateSMatrix(false, true);
			double[][] s2 = calculateSMatrix(true, true);
			double[][] s3 = calculateSMatrix(true, false);
			double[][] s4 = calculateSMatrix(false, false);
			
			//Finds the greater value in each savings matrix
			Point[] maxPoints = new Point[4];
			maxPoints[0] = getMaxValue(s1, s1);
			maxPoints[1] = getMaxValue(s2, s2);
			maxPoints[2] = getMaxValue(s3, s3);
			maxPoints[3] = getMaxValue(s4, s4);
			
			//Finds the best route merge
			Point maxPoint = maxPoints[0];
			
			for(int i=1; i<3; i++) {
				
				double[][] sMaxPoint = maxPoint.getS();
				double[][] sCurr = maxPoints[i].getS();
				
				if(sMaxPoint[maxPoint.getX()][maxPoint.getY()] < sCurr[maxPoints[i].getX()][maxPoints[i].getY()]) {
					maxPoint = maxPoints[i];
				}
			}
			
			
			
			tourNums--;
		}

		return null; // TODO Hay que cambiarlo
	}
	
	private ArrayList<Node> mergeTours(Point maxPoint) {
		
		ArrayList<Node> newTour = new ArrayList<Node>();
		
		ArrayList<Node> tourA = tours.get(maxPoint.getX());
		ArrayList<Node> tourB = tours.get(maxPoint.getY());
		
		
		if(maxPoint.getS().equals(s1)) {
			
			// Last A - First B			
			for(int i=0; i<tourA.size(); i++) {
				newTour.add(tourA.get(i));
			}
			
			for(int i=0; i<tourB.size(); i++) {
				newTour.add(tourB.get(i));
			}
			
			
		}else if(maxPoint.getS().equals(s2)) {
			
			// First A - First B
			for(int i=0; i<tourA.size(); i++) {
				//TODO 
			}
			
			
		}else if(maxPoint.getS().equals(s3)) {
			// First A - Last B
			
		}else {
			// Last A - Last B
			
		}
		
		return newTour;
		
	}
	
	private Point getMaxValue(double[][] s, double[][] matrix) {
		
		int minI = 0;
		int minJ = 0;
		
		
		for(int i=1; i<matrix.length; i++) {
			
			for(int j=(i+1); j<matrix[0].length; j++) {
				
				if(matrix[i][j] < matrix[minI][minJ]) {
					minI = i;
					minJ = j;
				}
				
			}
			
		}
		
		return new Point(s, minI, minJ);
		
	}
	
	private double[][] calculateSMatrix(boolean firstNodeA, boolean firstNodeB) {

		double[][] s1 = new double[tourNums][tourNums];

		ArrayList<Node> tourA;
		Node nodeA;

		ArrayList<Node> tourB;
		Node nodeB;

		for (int i = 0; i < s1.length; i++) {

			tourA = tours.get(i);
			
			if(firstNodeA == true) {
				nodeA = tourA.get(0); 
			}else {
				nodeA = tourA.get(tourA.size() - 1); 
			}
			

			for (int j = i + 1; j < s1[0].length; j++) {

				tourB = tours.get(j);
				
				if(firstNodeB == true) {
					nodeB = tourB.get(0);
				}else {
					nodeB = tourB.get(tourB.size()-1);
				}

				s1[i][j] = distMatrix[origin.getId()][nodeA.getId()]
						+ distMatrix[origin.getId()][nodeB.getId()]
						+ distMatrix[nodeA.getId()][nodeB.getId()];

			}

		}

		return s1;

	}
	
	

}
