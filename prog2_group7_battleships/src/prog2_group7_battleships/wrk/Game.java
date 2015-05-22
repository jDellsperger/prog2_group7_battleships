package prog2_group7_battleships.wrk;

import prog2_group7_battleships.enums.ShipType;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.enums.GameState;
import prog2_group7_battleships.enums.ReturnCode;

public class Game {

    private Player player1;
    private Player player2;
    private GameMode mode;
    private GameState state;

    public Game() {
        state = GameState.MODE_SELECT;
        player1 = new Player();
        player2 = new Player();
    }

    public ReturnCode placeShip(Orientation orientation, ShipType type, int xCoordinate, int yCoordinate) {
        ReturnCode returnCode;
        switch (state) {
            case P1_PLACEMENT:
                returnCode = this.player1.placeShip(orientation, type, xCoordinate, yCoordinate);
                if (this.player1.isDoneWithPlacement()) {
                    this.state = GameState.P2_PLACEMENT;
                }
                break;
            case P2_PLACEMENT:
                returnCode = this.player2.placeShip(orientation, type, xCoordinate, yCoordinate);
                if (this.player2.isDoneWithPlacement()) {
                    this.state = GameState.P1_SHOOTING;
                }
                break;
            default:
                returnCode = ReturnCode.INVALID_GAMESTATE;
        }
        return returnCode;
    }

    public GameState getGameState() {
        return this.state;
    }

    public void setGameMode(GameMode mode) {
        this.mode = mode;
        this.state = GameState.P1_PLACEMENT;
    }

    public ReturnCode shoot(int xCoordinate, int yCoordinate) {
        ReturnCode returnCode;
        switch (state) {
            case P1_SHOOTING:
                returnCode = this.player2.shotAt(xCoordinate, yCoordinate);
                switch (returnCode) {
                    case PLAYER_LOST:
                        this.state = GameState.GAME_OVER;
                        break;
                    case MISSED:
                        this.state = GameState.P2_SHOOTING;
                        break;
                }
                break;
            case P2_SHOOTING:
                returnCode = this.player1.shotAt(xCoordinate, yCoordinate);
                switch (returnCode) {
                    case PLAYER_LOST:
                        this.state = GameState.GAME_OVER;
                        break;
                    case SHIP_HIT:
                    case MISSED:
                        this.state = GameState.P1_SHOOTING;
                        break;
                }
                break;
            default:
                returnCode = ReturnCode.INVALID_GAMESTATE;
        }
        return returnCode;
    }

}
