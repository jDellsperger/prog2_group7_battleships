package prog2_group7_battleships.wrk;

import prog2_group7_battleships.enums.ShipType;

public class Ship {

    private final ShipType type;
    private boolean sunk;
    private int hitCount;
    private boolean placed;

    public Ship(ShipType type) {
        this.type = type;
        this.sunk = false;
        this.hitCount = 0;
        this.placed = false;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public boolean isPlaced() {
        return this.placed;
    }

    public ShipType getType() {
        return this.type;
    }

}
