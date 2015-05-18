package prog2_group7_battleships.wrk;

import prog2_group7_battleships.enums.Orientation;
import java.util.ArrayList;
import prog2_group7_battleships.enums.ReturnCode;

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

    ReturnCode placeShip(int xCoordinate, int yCoordinate, Orientation orientation, Ship ship) {
        ArrayList<Field> targetedFields = new ArrayList();
        ReturnCode returnCode;
        try {
            switch (orientation) {
                case HORIZONTAL:
                    for (int x = 0; x < ship.getType().getLength(); x++) {
                        if (!fields[x + xCoordinate][yCoordinate].isOccupied()) {
                            targetedFields.add(fields[x][yCoordinate]);
                        }
                    }
                    break;
                case VERTICAL:
                    for (int y = 0; y < ship.getType().getLength(); y++) {
                        if (!fields[xCoordinate][y + yCoordinate].isOccupied()) {
                            targetedFields.add(fields[xCoordinate][y]);
                        }
                    }
                    break;
            }
            if (targetedFields.size() == ship.getType().getLength()) {
                for (Field targetedField : targetedFields) {
                    targetedField.setShip(ship);
                }
                returnCode = ReturnCode.PLACED_SUCESSFULLY;
            } else {
                returnCode = ReturnCode.NOT_FREE;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            returnCode = ReturnCode.INDEX_OUT_OF_BOUND;
        }
        return returnCode;
    }

}
