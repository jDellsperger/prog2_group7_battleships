package prog2_group7_battleships.wrk;

import java.util.ArrayList;

public class Board {

    private Field[][] fields;
    private final int BOARD_LENGTH = 10;

    public Board() {
        this.fields = new Field[BOARD_LENGTH][BOARD_LENGTH];
        for (int x = 0; x < BOARD_LENGTH; x++) {
            for (int y = 0; y < BOARD_LENGTH; y++) {
                this.fields[x][y] = new Field();
            }
        }
    }

    boolean placeShip(int xCoordinate, int yCoordinate, Orientation orientation, Ship ship) {
        boolean shipPlaced = false;
        ArrayList<Field> targetedFields = new ArrayList();
        switch (orientation) {
            case HORIZONTAL:
                for (int x = 0; x < ship.getType().getLength(); x++) {
                    if (!fields[x][yCoordinate].isOccupied()) {
                        targetedFields.add(fields[x][yCoordinate]);
                    }
                }
                break;
            case VERTICAL:
                for (int y = 0; y < ship.getType().getLength(); y++) {
                    if (!fields[xCoordinate][y].isOccupied()) {
                        targetedFields.add(fields[xCoordinate][y]);
                    }
                }
                break;
        }
        System.out.println("targetedFields.size: " + targetedFields.size());
        System.out.println("ship.type.size: " + ship.getType().getLength());
        if (targetedFields.size() == ship.getType().getLength()) {
            for (Field targetedField : targetedFields) {
                targetedField.setShip(ship);
            }
            shipPlaced = true;
        }
        return shipPlaced;
    }

}
