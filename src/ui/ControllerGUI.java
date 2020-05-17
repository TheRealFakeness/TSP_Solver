package ui;

import java.awt.Button;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.*;

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
		
	}
	
}
