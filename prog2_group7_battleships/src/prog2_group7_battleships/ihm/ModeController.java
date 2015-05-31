package prog2_group7_battleships.ihm;

import prog2_group7_battleships.enums.GameMode;
import javafx.fxml.FXML;

public class ModeController {

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
