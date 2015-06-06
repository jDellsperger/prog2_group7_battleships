package prog2_group7_battleships.ihm;

import javafx.fxml.FXML;

public class GameOverLayoutController {

    private GUIView view;

    public void setView(GUIView view) {
        this.view = view;
    }

    @FXML
    private void startNewGame() {
        this.view.startNewGame();
    }

}
