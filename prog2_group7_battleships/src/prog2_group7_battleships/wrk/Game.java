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
                break;
            case P2_PLACEMENT:
                returnCode = this.player2.placeShip(orientation, type, xCoordinate, yCoordinate);
                break;
            default:
                returnCode = ReturnCode.INVALID_GAMESTATE;
        }
        return returnCode;
    }

    public void setGameMode(GameMode mode) {
        this.mode = mode;
    }

}
