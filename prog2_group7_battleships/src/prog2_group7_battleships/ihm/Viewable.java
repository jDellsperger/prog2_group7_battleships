package prog2_group7_battleships.ihm;

import prog2_group7_battleships.ctrl.Controller;

public interface Viewable {

    void setController(Controller ctrl);

    void queryPlacement();

    void displayMessage(String message);

    void queryMode();

    void queryShooting();

    void updateView();

}
