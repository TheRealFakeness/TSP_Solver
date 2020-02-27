package model;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

public class Opt3Move implements Solver{
	
	public enum OptCase {
		CASE0, CASE1, CASE2, CASE3, CASE4, CASE5, CASE6, CASE7;
	}
	
	
	
	@Override
	public Solution solve(Model model) {
		
		
		return null;
	}
	
	
	public Solution Opt3Move(Model model) {
		
		OptCase[] optCases = OptCase.class.getEnumConstants();
		
		Node[] nodes = model.getNodes();
		int size = nodes.length;
		
		double[][] distMatrix = model.getDistMatrix();
		Node[] route = model.getRoute();
		
		
		boolean foundImprovement = false;
		
		while(!foundImprovement) {
			
			for(int counter_1=0; counter_1<size; counter_1++) {
				
				int i = counter_1;
				int X1 = route[i].getId();
				int X2 = route[(i+1) % size].getId();
				
				for(int counter_2=1; counter_2<size-3; counter_2++) {
					
					int j = (i + counter_2) % size;
					int Y1 = route[j].getId();
					int Y2 = route[(j+1) % size].getId();
					
					
					for(int counter_3=j+1; counter_3<size-1; counter_3++) {
						
						int k = (i + counter_3) % size;
						int Z1 = route[k].getId();
						int Z2 = route[(k+1) % size].getId();
						
						for(int optCase=0; optCase<optCases.length; optCase++) {
							
							if(gainExpected(X1, X2, Y1, Y2, Z1, Z2, optCases[optCase], model) < 0) {
							
								// 3-Opt Move
								
							}
						}
						
					} // End z for
				} //End j for
			} //End i for
		} // End While
		
		
		return null;
	}
	
	public double gainExpected(int X1, int X2, int Y1, int Y2, int Z1, int Z2, OptCase optCase, Model model) {
		
		double[][] distMatrix = model.getDistMatrix();
 		double delLength = 0;
		double addLength = 0;
		
		switch(optCase) {
		
			case CASE0:
				return 0;
	
		//2-Opt Moves
			case CASE1: 
				delLength = distMatrix[X1][X2] + distMatrix[Z1][Z2];
				addLength = distMatrix[X1][Z1] + distMatrix[X2][Z2];
				break;
			case CASE2:
				delLength = distMatrix[Y1][Y2] + distMatrix[Z1][Z2];
				addLength = distMatrix[Y1][Z1] + distMatrix[X2][Z2];
				break;
			case CASE3:
				delLength = distMatrix[X1][X2] + distMatrix[Y1][Y2];
				addLength = distMatrix[X1][Y1] + distMatrix[X2][Y2];
				break;
				
		//3-Opt Moves	
			case CASE4:
				addLength = distMatrix[X1][Y1] + distMatrix[X2][Z2] + distMatrix[Y2][Z2];
				break;
			case CASE5:
				addLength = distMatrix[X1][Z1] + distMatrix[Y2][X2] + distMatrix[Y1][Z2];
				break;
			case CASE6:
				addLength = distMatrix[X1][Y2] + distMatrix[Z1][Y1] + distMatrix[X2][Z2];
				break;
			case CASE7:
				addLength = distMatrix[X1][Y2] + distMatrix[Z1][X2] + distMatrix[Y1][Z2];
				break;
	
		}
		
		if(optCase == OptCase.CASE4 || optCase == OptCase.CASE5 || optCase == OptCase.CASE6 || optCase == OptCase.CASE7) {
			delLength = distMatrix[X1][X2] + distMatrix[Y1][Y2] + distMatrix[Z1][Z2];
		}
		
		return addLength - delLength;
	}
	
	
	
}
