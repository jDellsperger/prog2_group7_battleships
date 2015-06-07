package prog2_group7_battleships.ctrl;

import java.util.ArrayList;
import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.enums.GameState;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ReturnCode;
import prog2_group7_battleships.enums.ShipType;
import prog2_group7_battleships.ihm.Viewable;
import prog2_group7_battleships.wrk.Field;
import prog2_group7_battleships.wrk.Game;
import prog2_group7_battleships.wrk.Ship;

public class Controller {

    private final Viewable view;
    private Game game;

    public Controller(Viewable view, Game game) {
        this.view = view;
        this.game = game;
    }

    public void start() {
        this.stateSwitch();
    }

    public void setGameMode(GameMode mode) {
        this.game.setGameMode(mode);
        this.stateSwitch();
    }

    private void stateSwitch() {
        switch (this.game.getGameState()) {
            case MODE_SELECT:
                this.view.queryMode();
                break;
            case P1_PLACEMENT:
                this.view.displayTitle("Player 1 ship placement");
                this.view.queryPlacement();
                break;
            case P2_PLACEMENT:
                if (this.game.getGameMode() == GameMode.MULTI) {
                    this.view.displayTitle("Player 2 ship placement");
                    this.view.queryPlacement();
                } else {
                    this.view.displayTitle("AI ship placement");
                    this.view.displayMessage(this.game.aiPlacement().getMessage());
                    this.stateSwitch();
                }
                break;
            case P1_SHOOTING:
                this.view.displayTitle("Player 1 shooting");
                this.view.queryShooting();
                break;
            case P2_SHOOTING:
                if (this.game.getGameMode() == GameMode.MULTI) {
                    this.view.displayTitle("Player 2 shooting");
                    this.view.queryShooting();
                } else {
                    this.view.displayTitle("AI shooting");
                    this.view.displayMessage(this.game.aiShoot().getMessage());
                    this.stateSwitch();
                }
                break;
            case GAME_OVER:
                this.view.displayGameOver();
                break;
            default:
                this.view.displayMessage("Invalid state");
        }
    }

    public void placeShip(Orientation orientation, ShipType type, int xCoordinate, int yCoordinate) {
        ReturnCode returnCode = this.game.placeShip(orientation, type, xCoordinate, yCoordinate);
        this.view.displayMessage(returnCode.getMessage());
        if (this.game.getGameMode() == GameMode.MULTI && returnCode == ReturnCode.PLAYER_DONE_WITH_PLACEMENT) {
            this.view.queryPlayerSwitch();
        } else {
            this.stateSwitch();
        }
    }

    public void shoot(int xCoordinate, int yCoordinate) {
        ReturnCode returnCode = this.game.shoot(xCoordinate, yCoordinate);
        this.view.displayMessage(returnCode.getMessage());
        if (this.game.getGameMode() == GameMode.MULTI && returnCode == ReturnCode.MISSED) {
            this.view.queryPlayerSwitch();
        } else {
            this.stateSwitch();
        }
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

    public void startNewGame() {
        this.game = new Game();
        this.stateSwitch();
    }

    public void playerSwitched() {
        this.stateSwitch();
    }
    
    public int getPlayer1ShotCount() {
        return this.game.getPlayer1ShotCount();
    }
    
    public int getPlayer2ShotCount() {
        return this.game.getPlayer2ShotCount();
    }
    
    public int getPlayer1HitCount() {
        return this.game.getPlayer1HitCount();
    }
    
    public int getPlayer2HitCount() {
        return this.game.getPlayer2HitCount();
    }
    
    public int getPlayer1ShotHitRatio() {
        return this.game.getPlayer1ShotHitRatio();
    }
    
    public int getPlayer2ShotHitRatio() {
        return this.game.getPlayer2ShotHitRatio();
    }

    public ArrayList<Ship> getPlayer1Ships() {
        return this.game.getPlayer1Ships();
    }

    public ArrayList<Ship> getPlayer2Ships() {
        return this.game.getPlayer2Ships();
    }
    
    public ArrayList<ShipType> getUnplacedShipTypes() {
        return this.game.getUnplacedShipTypes();
    }
    
    public String getActivePlayerName() {
        return this.game.getActivePlayerName();
    }
    
    public String getInactivePlayerName() {
        return this.game.getInactivePlayerName();
    }

}
