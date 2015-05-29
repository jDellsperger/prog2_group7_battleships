package prog2_group7_battleships.wrk;

import prog2_group7_battleships.enums.Orientation;
import java.util.ArrayList;
import prog2_group7_battleships.enums.ReturnCode;

public class Board {

    private final Field[][] fields;
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
                        Field currentField = fields[x + xCoordinate][yCoordinate];
                        if (null == currentField.getShip() && !currentField.isNextToShip()) {
                            targetedFields.add(currentField);
                        }
                    }
                    break;
                case VERTICAL:
                    for (int y = 0; y < ship.getType().getLength(); y++) {
                        Field currentField = fields[xCoordinate][y + yCoordinate];
                        if (null == currentField.getShip() && !currentField.isNextToShip()) {
                            targetedFields.add(currentField);
                        }
                    }
                    break;
            }
            if (targetedFields.size() == ship.getType().getLength()) {
                for (Field targetedField : targetedFields) {
                    targetedField.setShip(ship);
                }
                switch (orientation) {
                    case HORIZONTAL:
                        for (int y = -1; y <= 1; y++) {
                            if (yCoordinate + y >= 0 && yCoordinate + y < this.BOARD_LENGTH) {
                                for (int x = -1; x < ship.getType().getLength() + 1; x++) {
                                    if (xCoordinate + x >= 0 && xCoordinate + x < this.BOARD_LENGTH) {
                                        fields[xCoordinate + x][yCoordinate + y].setNextToShip(true);
                                    }
                                }
                            }
                        }
                        break;
                    case VERTICAL:
                        for (int x = -1; x <= 1; x++) {
                            if (xCoordinate + x >= 0 && xCoordinate + x < this.BOARD_LENGTH) {
                                for (int y = -1; y < ship.getType().getLength() + 1; y++) {
                                    if (yCoordinate + y >= 0 && yCoordinate + y < this.BOARD_LENGTH) {
                                        fields[xCoordinate + x][yCoordinate + y].setNextToShip(true);
                                    }
                                }
                            }
                        }
                        break;
                }
                returnCode = ReturnCode.PLACED_SUCCESSFULLY;
            } else {
                returnCode = ReturnCode.NOT_FREE;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            returnCode = ReturnCode.INDEX_OUT_OF_BOUND;
        }
        return returnCode;
    }

    public ReturnCode shotAt(int xCoordinate, int yCoordinate) {
        ReturnCode returnCode;
        try {
            Field targetedField = fields[xCoordinate][yCoordinate];
            if (!targetedField.isShotAt()) {
                targetedField.setShotAt(true);
                Ship targetedShip = targetedField.getShip();
                if (null != targetedShip) {
                    targetedShip.wasHit();
                    if (targetedShip.isSunk()) {
                        returnCode = ReturnCode.SHIP_SUNK;
                    } else {
                        returnCode = ReturnCode.SHIP_HIT;
                    }
                } else {
                    returnCode = ReturnCode.MISSED;
                }
            } else {
                returnCode = ReturnCode.ALREADY_SHOT_AT;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            returnCode = ReturnCode.INDEX_OUT_OF_BOUND;
        }
        return returnCode;
    }

}
