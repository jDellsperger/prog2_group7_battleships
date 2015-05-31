package prog2_group7_battleships.ctrl;

import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.enums.GameState;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ShipType;
import prog2_group7_battleships.ihm.Viewable;
import prog2_group7_battleships.wrk.Field;
import prog2_group7_battleships.wrk.Game;

public class Controller {

    private final Viewable view;
    private final Game game;

    public Controller(Viewable view, Game game) {
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
                if (this.game.getGameMode() == GameMode.MULTI) {
                    this.view.displayMessage("Player 2 ship placement");
                    this.view.queryPlacement();
                } else {
                    this.view.displayMessage("AI ship placement");
                    this.view.displayMessage(this.game.aiPlacement().getMessage());
                    this.stateSwitch();
                }
                break;
            case P1_SHOOTING:
                this.view.displayMessage("Player 1 shooting");
                this.view.queryShooting();
                break;
            case P2_SHOOTING:
                if (this.game.getGameMode() == GameMode.MULTI) {
                    this.view.displayMessage("Player 2 shooting");
                    this.view.queryShooting();
                } else {
                    this.view.displayMessage("AI shooting");
                    this.view.displayMessage(this.game.aiShoot().getMessage());
                    this.stateSwitch();
                }
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

    public Field[][] getActivePlayerFields() {
        return this.game.getActivePlayerFields();
    }

    public Field[][] getInactivePlayerFields() {
        return this.game.getInactivePlayerFields();
    }

	public GameState getGameState() {
		return this.game.getGameState();
	}
    
    

}