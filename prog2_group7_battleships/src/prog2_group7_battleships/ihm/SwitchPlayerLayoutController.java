package prog2_group7_battleships.ihm;

import javafx.fxml.FXML;

public class SwitchPlayerLayoutController {

    private GUIView view;

    public void setView(GUIView view) {
        this.view = view;
    }

    @FXML
    private void handleConfirmation() {
        this.view.playerSwitched();
    }

}
