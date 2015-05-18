package prog2_group7_battleships.wrk;

import prog2_group7_battleships.enums.ShipType;
import java.util.ArrayList;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ReturnCode;

public class Player {

    private final ArrayList<Ship> ships;
    private Board board;

    public Player() {
        this.board = new Board();
        ships = new ArrayList();
        for (ShipType value : ShipType.values()) {
            Ship ship = new Ship(value);
            ships.add(ship);
        }
    }

    private Ship getUnplacedShipByType(ShipType type) {
        for (Ship ship : ships) {
            if (ship.getType() == type && !ship.isPlaced()) {
                return ship;
            }
        }
        return null;
    }

    public ReturnCode placeShip(Orientation orientation, ShipType type, int xCoordinate, int yCoordinate) {
        ReturnCode returnCode;

        // check if player still has ship available
        Ship ship = this.getUnplacedShipByType(type);
        if (null != ship) {
            returnCode = this.board.placeShip(xCoordinate, yCoordinate, orientation, ship);
            if (returnCode == ReturnCode.PLACED_SUCESSFULLY) {
                ship.setPlaced(true);
            }
        } else {
            returnCode = ReturnCode.NOT_AVAILABLE;
        }

        return returnCode;
    }

    public boolean isDoneWithPlacement() {
        boolean isDone = true;
        for (Ship ship : ships) {
            if (!ship.isPlaced()) {
                isDone = false;
                break;
            }
        }
        return isDone;
    }

}
