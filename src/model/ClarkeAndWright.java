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
	private double[][] s1;

	/*
	 * Savings matrix of joining the first node of tour i with the last node of the
	 * tour j
	 */
	private double[][] s2;

	/*
	 * Savings matrix of joining the last node of tour i with the first node of tour
	 * j
	 */
	private double[][] s3;

	/*
	 * Savings matrix of joining the last node of tour i with the last node of tour
	 * j
	 */
	private double[][] s4;

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
		double cost = calculateRouteCost(route);
		
		return new Solution(route, cost);
	}

	private Node[] algorithm() {

		if (origin == null) {
			Random r = new Random();
			origin = nodes[r.nextInt(nodes.length)];
		}

		tours = new ArrayList<ArrayList<Node>>();
		
		//Inicializo los primeros tours
		for (int k = 0; k < nodes.length; k++) {

			if (nodes[k].equals(origin) == false) {
				ArrayList<Node> tour = new ArrayList<Node>();
				tour.add(nodes[k]);

				tours.add(tour);

			}

		}

		ArrayList<Node> newTour = null;
		
		while (tourNums > 1) {
			
			//Calculates each savings matrix
			s1 = calculateSMatrix(false, true);
			s2 = calculateSMatrix(true, true);
			s3 = calculateSMatrix(true, false);
			s4 = calculateSMatrix(false, false);
			
			printMatrix(s1);
			
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
				
				if(sMaxPoint[maxPoint.getI()][maxPoint.getJ()] < sCurr[maxPoints[i].getI()][maxPoints[i].getJ()]) {
					maxPoint = maxPoints[i];
				}
			}

			//System.out.println("i: " + maxPoint.getI());
			//System.out.println("j: " + maxPoint.getJ());
			
			//Removes old tours
			if(maxPoint.getI() < maxPoint.getJ()) {
				tours.remove(maxPoint.getI());
				tours.remove(maxPoint.getJ()-1);
			}else if(maxPoint.getI() < maxPoint.getJ()) {
				tours.remove(maxPoint.getJ());
				tours.remove(maxPoint.getI()-1);
			}else {
				tours.remove(maxPoint.getJ());
			}
			
			//Merges the 2 tours with the highest savings
			newTour = mergeTours(maxPoint);
			tours.add(newTour);
			
			tourNums--;
		}

		Node[] tour = new Node[tours.get(0).size()];
		
		for(int i=0; i<tour.length; i++) {
			tour[i] = tours.get(0).get(i);
		}
		
		return tour;
	}
	
	private ArrayList<Node> mergeTours(Point maxPoint) {
		
		ArrayList<Node> newTour = new ArrayList<Node>();
		
		ArrayList<Node> tourA = tours.get(maxPoint.getI());
		ArrayList<Node> tourB = tours.get(maxPoint.getJ());
		
		
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
				newTour.add(tourA.get(tourA.size()-1-i));
			}
			
			for(int i=0; i<tourB.size(); i++) {
				newTour.add(tourB.get(i));
			}
			
			
		}else if(maxPoint.getS().equals(s3)) {
			// First A - Last B
			for(int i=0; i<tourA.size(); i++) {
				newTour.add(tourA.get(i));
			}
			
			for(int i=0; i<tourB.size(); i++) {
				newTour.add(tourB.get(tourB.size()-1-i));
			}
			
		}else {
			// Last A - Last B
			for(int i=0; i<tourA.size(); i++) {
				newTour.add(tourA.get(tourA.size()-1-i)); 
			}
			
			for(int i=0; i<tourB.size(); i++) {
				newTour.add(tourB.get(tourB.size()-1-i));
			}
		}
		
		return newTour;
		
	}
	
	private Point getMaxValue(double[][] s, double[][] matrix) {
		
		int minI = 0;
		int minJ = 1;
		
		
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
						- distMatrix[nodeA.getId()][nodeB.getId()];

			}

		}

		return s1;

	}
	
	/*
	 * Calculates the cost of the tour
	 * 
	 * @return double  the cost of the tour
	 */
	private double calculateRouteCost(Node[] nodes) {
		double cost = 0;
		for(int i=0; i<nodes.length-1; i++) {
			
			cost += distMatrix[nodes[i].getId()][nodes[i+1].getId()];
			
		}
		
		// Adds the cost from the last node of the tour to the initial node of the tour
		cost += distMatrix[nodes[nodes.length-1].getId()][nodes[0].getId()];
		
		return cost;
	}
	
	private void printMatrix(double[][] m) {
		
		System.out.println();
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				System.out.print(m[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

}
