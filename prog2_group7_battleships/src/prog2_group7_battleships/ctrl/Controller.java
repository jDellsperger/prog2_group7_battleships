package prog2_group7_battleships.ctrl;

import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.ihm.View;
import prog2_group7_battleships.wrk.Game;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ShipType;

public class Controller {

    private final View view;
    private final Game game;

    public Controller(View view, Game game) {
        this.view = view;
        this.game = game;
    }

    public void start() {
        this.view.queryMode();
    }

    public void setGameMode(GameMode mode) {
        this.game.setGameMode(mode);
    }

    public void placeShip(Orientation orientation, ShipType type, int xCoordinate, int yCoordinate) {
        this.view.displayMessage(this.game.placeShip(orientation, type, xCoordinate, yCoordinate).getMessage());
        this.view.queryPlacement();
    }

}
