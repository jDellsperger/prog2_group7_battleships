package prog2_group7_battleships.enums;

public enum ShipType {

    CARRIER(5, "Carrier"), BATTLESHIP(4, "Battleship"), SUBMARINE(3, "Submarine"),
    DESTROYER(3, "Destroyer"), BOAT(2, "Boat");

    private final int length;
    private final String name;

    private ShipType(int length, String name) {
        this.length = length;
        this.name = name;
    }

    public int getLength() {
        return this.length;
    }

}
