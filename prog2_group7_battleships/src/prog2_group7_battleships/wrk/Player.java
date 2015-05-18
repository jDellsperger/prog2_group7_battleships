package prog2_group7_battleships.wrk;

import java.util.ArrayList;

public class Player {

    private final ArrayList<Ship> ships;

    public Player() {
        ships = new ArrayList();
        for (ShipType value : ShipType.values()) {
            Ship ship = new Ship(value);
            ships.add(ship);
        }
    }

    public Ship getUnplacedShipByType(ShipType type) {
        for (Ship ship : ships) {
            if (ship.getType() == type && !ship.isPlaced()) {
                return ship;
            }
        }
        return null;
    }

}
