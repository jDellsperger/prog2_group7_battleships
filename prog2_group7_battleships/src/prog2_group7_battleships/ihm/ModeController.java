package prog2_group7_battleships.ihm;

import prog2_group7_battleships.enums.GameMode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ModeController {

	@FXML
	private Button singlePlayer;
	@FXML
	private Button multiPlayer;
	
	private GUIView view;
	
	public void setView(GUIView view) {
		this.view = view;
	}
	
	@FXML
    public void handleSinglePlayerMode() {
        this.view.setMode(GameMode.SINGLE);
    }
	
	@FXML
    public void handleMultiPlayerMode() {
		this.view.setMode(GameMode.MULTI);
    }
}
