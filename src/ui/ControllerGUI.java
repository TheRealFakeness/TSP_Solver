package ui;

import java.awt.Button;
import java.awt.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.*;
import sun.swing.plaf.synth.Paint9Painter;

public class ControllerGUI {

	@FXML
	private ToggleGroup algorithm;

	@FXML
	private Canvas canvas;

	@FXML
	void runAlgorithm(ActionEvent event) {

		RadioButton rb = (RadioButton) algorithm.getToggles();
		String algorithm = rb.getText();
		
		if(algorithm.contentEquals("Nearest Neighbor")) {
			nearestNeighbor();
		}else if(algorithm.contentEquals("Sweep")) {
			sweep();
		}else if(algorithm.contentEquals("Clark And Wright")) {
			clarkAndWright();
		}else if(algorithm.contentEquals("Opt3Move")) {
			opt3Move();
		}
		
		
	}
	
	private void nearestNeighbor() {
		
	}
	
	private void sweep() {
		
	}
	
	private void clarkAndWright() {
		
	}
	
	private void opt3Move() {
		
	}
	
	public void plotRoute(Solution sol) {
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Node[] route = sol.getRoute();
		
		
		for(int z=0; z<route.length-1; z++) {
			
			Node i = route[z];
			Node j = route[z+1];
			
			
			gc.setStroke(javafx.scene.paint.Color.BLACK);
			gc.fillOval(i.getxCoord(), i.getyCoord(), 5, 5);
			
			gc.setStroke(javafx.scene.paint.Color.RED);
			gc.strokeLine(i.getxCoord(), i.getyCoord(), j.getxCoord(), j.getyCoord());
			
			
		}
		
		Node last = route[route.length];
		Node first = route[0];
		
		gc.strokeLine(last.getxCoord(), last.getyCoord(), first.getxCoord(), first.getyCoord());
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
