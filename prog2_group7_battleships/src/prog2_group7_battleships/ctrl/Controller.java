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
        this.stateSwitch();
    }

    public void placeShip(Orientation orientation, ShipType type, int xCoordinate, int yCoordinate) {
        this.view.displayMessage(this.game.placeShip(orientation, type, xCoordinate, yCoordinate).getMessage());
        this.stateSwitch();
    }

    private void stateSwitch() {
        switch (this.game.getGameState()) {
            case P1_PLACEMENT:
                this.view.displayMessage("Player 1 ship placement");
                this.view.queryPlacement();
                break;
            case P2_PLACEMENT:
                this.view.displayMessage("Player 2 ship placement");
                this.view.queryPlacement();
                break;
            case P1_SHOOTING:
                this.view.displayMessage("Player 1 shooting");
                this.view.queryShooting();
                break;
            case P2_SHOOTING:
                this.view.displayMessage("Player 2 shooting");
                this.view.queryShooting();
                break;
            case GAME_OVER:
                this.view.displayMessage("The game is over.");
                System.exit(0);
                break;
            default:
                this.view.displayMessage("Invalid state");
        }
    }

    public void shoot(int xCoordinate, int yCoordinate) {
        this.view.displayMessage(this.game.shoot(xCoordinate, yCoordinate).getMessage());
        this.stateSwitch();
    }

}
