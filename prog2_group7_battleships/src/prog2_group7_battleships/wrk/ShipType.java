package prog2_group7_battleships.wrk;

public enum ShipType {

    CARRIER(5), BATTLESHIP(4), SUBMARINE(3), DESTROYER(3), BOAT(2);

    private final int length;

    private ShipType(int length) {
        this.length = length;
    }

    public int getLength() {
        return this.length;
    }

}
