package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SolutionTest {

	private Solution sol;
	
	public void setupStage1() {
		Node[] route = {new Node(1, 10, -20), new Node(2, -8, 12), new Node(3, 0, -2),
				new Node(4, 8, 22)};
		
		sol = new Solution(route, 200);
	}
	
	@Test
	public void testGetRoute() {
		setupStage1();
		
		assertEquals(sol.getRoute()[0].getId(), 1);
		assertEquals(sol.getRoute()[1].getxCoord(), -8);
		assertEquals(sol.getRoute()[3].getyCoord(), 22);
	}
	
	@Test
	public void testGetRouteCost() {
		setupStage1();
		
		assertEquals(sol.getRouteCost(), 200);
	}
}
