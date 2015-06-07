package prog2_group7_battleships.wrk;

import prog2_group7_battleships.enums.ShipType;
import java.util.ArrayList;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ReturnCode;

public class Player {

    private final ArrayList<Ship> ships;
    private final Board board;
    private Double shotCounter;
    private Double hitCounter;
    private String name;

    public Player(String name) {
        this.name = name;
        this.shotCounter = 0.0;
        this.hitCounter = 0.0;
        this.board = new Board();
        this.ships = new ArrayList();
        for (ShipType value : ShipType.values()) {
            Ship ship = new Ship(value);
            this.ships.add(ship);
        }
    }

    private Ship getUnplacedShipByType(ShipType type) {
        for (Ship ship : this.ships) {
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
            if (returnCode == ReturnCode.PLACED_SUCCESSFULLY) {
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

    public ReturnCode shotAt(int xCoordinate, int yCoordinate) {
        ReturnCode returnCode = this.board.shotAt(xCoordinate, yCoordinate);
        if (returnCode == ReturnCode.SHIP_SUNK && !this.hasUnsunkShips()) {
            returnCode = ReturnCode.PLAYER_LOST;
        }
        return returnCode;
    }

    private boolean hasUnsunkShips() {
        boolean hasUnsunkShips = false;
        for (Ship ship : this.ships) {
            if (!ship.isSunk()) {
                hasUnsunkShips = true;
                break;
            }
        }
        return hasUnsunkShips;
    }

    public Field[][] getFields() {
        return this.board.getFields();
    }

    public int getShotCount() {
        return this.shotCounter.intValue();
    }

    public int getHitCount() {
        return this.hitCounter.intValue();
    }

    public int getShotHitRatio() {
        if (this.shotCounter > 0) {
            Double ratio = (hitCounter / shotCounter) * 100;
            return ratio.intValue();
        }
        return 0;
    }

    public void incrementShotCount() {
        this.shotCounter++;
    }

    public void incrementHitCount() {
        this.hitCounter++;
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }

    public ArrayList<ShipType> getUnplacedShipTypes() {
        ArrayList<ShipType> unplacedShipTypes = new ArrayList();
        for (ShipType type : ShipType.values()) {
            if(null != this.getUnplacedShipByType(type)) {
                unplacedShipTypes.add(type);
            }
        }
        return unplacedShipTypes;
    }

    public String getName() {
        return this.name;
    }

}
