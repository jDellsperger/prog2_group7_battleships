package prog2_group7_battleships.ihm;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class RootLayoutController {

	@FXML
	private Label statusMessage;
	
	private GUIView view;
	
	public void initialize() {
	}
	
	public void setView(GUIView view) {
		this.view = view;
	}
	
    @FXML
    public void handleNew() {
    	this.view.startNewGame();
    }

    @FXML
    public void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Battleship");
        alert.setHeaderText("About");
        alert.setContentText("Authors:\nDellsperger Jan\nEllenberger Roger\nSpring Mathias" + "\n\n" + "A project for the BFH Bern \nUniversity of Applied Sciences");

        alert.showAndWait();
    }

    @FXML
    public void handleExit() {
        System.exit(0);
    }
    
	public void setStatusMessage(String message) {
		this.statusMessage.setText(message);
	}

}
