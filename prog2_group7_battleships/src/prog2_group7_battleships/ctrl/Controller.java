package prog2_group7_battleships.ctrl;

import prog2_group7_battleships.ihm.View;
import prog2_group7_battleships.wrk.Game;
import prog2_group7_battleships.wrk.Orientation;
import prog2_group7_battleships.wrk.ShipType;

public class Controller {

    private final View view;
    private final Game game;

    public Controller(View view, Game game) {
        this.view = view;
        this.game = game;
    }

    public void start() {
        this.view.queryPlacement();
    }

    public void placeShip(Orientation orientation, ShipType type, int xCoordinate, int yCoordinate) {
        if (this.game.placeShip(orientation, type, xCoordinate, yCoordinate)) {
            System.out.println("Controller placeShip: Ship was placed at coordinates x:"
                    + xCoordinate + ", y:" + yCoordinate + " with length " + type.getLength());
        } else {
            System.out.println("Ship placement failed...");
        }
    }

}
