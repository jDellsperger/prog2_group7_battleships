package prog2_group7_battleships.ihm;

import javafx.fxml.FXML;

public class SwitchPlayerLayoutController {

    private GUIView view;

    private boolean confirmed;

    @FXML
    private void initialize() {
        this.confirmed = false;
    }

    public void setView(GUIView view) {
        this.view = view;
    }

    public void waitForUserConfirmation() {
        while (this.confirmed) {
            try {
                wait(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.confirmed = false;
    }

    @FXML
    private void handleConfirmation() {
        this.confirmed = true;
    }

}
