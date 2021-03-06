package prog2_group7_battleships.wrk;

import java.util.ArrayList;
import prog2_group7_battleships.enums.AIShotType;
import prog2_group7_battleships.enums.ShipType;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.enums.GameState;
import prog2_group7_battleships.enums.ReturnCode;

public class Game {

    private Player player1;
    private Player player2;
    private Player winner;
    private GameMode mode;
    private GameState state;

    // Attributes for AI Game
    private int hitShipXCoordinate;
    private int hitShipYCoordinate;
    private int hitShipOffset;
    private Orientation hitShipOrientation;
    private AIShotType shotType;

    public Game() {
        this.state = GameState.MODE_SELECT;
        this.shotType = AIShotType.RANDOM_SHOT;
    }

    public ReturnCode placeShip(Orientation orientation, ShipType type, int xCoordinate, int yCoordinate) {
        ReturnCode returnCode;
        switch (state) {
            case P1_PLACEMENT:
                returnCode = this.player1.placeShip(orientation, type, xCoordinate, yCoordinate);
                if (this.player1.isDoneWithPlacement()) {
                    this.state = GameState.P2_PLACEMENT;
                    returnCode = ReturnCode.PLAYER_DONE_WITH_PLACEMENT;
                }
                break;
            case P2_PLACEMENT:
                returnCode = this.player2.placeShip(orientation, type, xCoordinate, yCoordinate);
                if (this.player2.isDoneWithPlacement()) {
                    this.state = GameState.P1_SHOOTING;
                    returnCode = ReturnCode.PLAYER_DONE_WITH_PLACEMENT;
                }
                break;
            default:
                returnCode = ReturnCode.INVALID_GAMESTATE;
        }
        return returnCode;
    }

    public GameState getGameState() {
        return this.state;
    }

    public void setGameMode(GameMode mode) {
        this.mode = mode;
        this.player1 = new Player("Player 1");
        if (this.mode == GameMode.MULTI) {
            this.player2 = new Player("Player 2");
        } else {
            this.player2 = new Player("AI");
        }
        this.state = GameState.P1_PLACEMENT;
    }

    public ReturnCode shoot(int xCoordinate, int yCoordinate) {
        ReturnCode returnCode;
        switch (this.state) {
            case P1_SHOOTING:
                returnCode = this.player2.shotAt(xCoordinate, yCoordinate);
                switch (returnCode) {
                    case PLAYER_LOST:
                        this.player1.incrementShotCount();
                        this.player1.incrementHitCount();
                        this.winner = this.player1;
                        this.state = GameState.GAME_OVER;
                        break;
                    case MISSED:
                        this.player1.incrementShotCount();
                        this.state = GameState.P2_SHOOTING;
                        break;
                    case SHIP_HIT:
                    case SHIP_SUNK:
                        this.player1.incrementShotCount();
                        this.player1.incrementHitCount();
                        break;
                }
                break;
            case P2_SHOOTING:
                returnCode = this.player1.shotAt(xCoordinate, yCoordinate);
                switch (returnCode) {
                    case PLAYER_LOST:
                        this.player2.incrementShotCount();
                        this.player2.incrementHitCount();
                        this.winner = this.player2;
                        this.state = GameState.GAME_OVER;
                        break;
                    case MISSED:
                        this.player2.incrementShotCount();
                        this.state = GameState.P1_SHOOTING;
                        break;
                    case SHIP_HIT:
                    case SHIP_SUNK:
                        this.player2.incrementShotCount();
                        this.player2.incrementHitCount();
                        break;
                }
                break;
            default:
                returnCode = ReturnCode.INVALID_GAMESTATE;
        }
        return returnCode;
    }

    public ReturnCode aiPlacement() {
        for (ShipType type : ShipType.values()) {
            ReturnCode placementCode = ReturnCode.INDEX_OUT_OF_BOUND;
            Double xCoordinateDouble = Math.random() * 10;
            Double yCoordinateDouble = Math.random() * 10;
            int xCoordinate = xCoordinateDouble.intValue();
            int yCoordinate = yCoordinateDouble.intValue();
            Orientation orientation;
            Double randomOrientation = Math.random() * 10;
            if (randomOrientation.intValue() % 2 == 0) {
                orientation = Orientation.HORIZONTAL;
            } else {
                orientation = Orientation.VERTICAL;
            }
            while (placementCode != ReturnCode.PLACED_SUCCESSFULLY) {
                switch (placementCode) {
                    case INDEX_OUT_OF_BOUND:
                        if (orientation == Orientation.HORIZONTAL) {
                            if (xCoordinate + type.getLength() > Board.BOARD_LENGTH - 1) {
                                xCoordinate = Board.BOARD_LENGTH - 1 - type.getLength();
                            }
                            if (yCoordinate > Board.BOARD_LENGTH - 1) {
                                yCoordinate = Board.BOARD_LENGTH - 1;
                            }
                        } else {
                            if (yCoordinate + type.getLength() > Board.BOARD_LENGTH - 1) {
                                yCoordinate = Board.BOARD_LENGTH - 1 - type.getLength();
                            }
                            if (xCoordinate > Board.BOARD_LENGTH - 1) {
                                xCoordinate = Board.BOARD_LENGTH - 1;
                            }
                        }
                        break;
                    case NOT_FREE:
                        if (orientation == Orientation.HORIZONTAL) {
                            if (yCoordinate == Board.BOARD_LENGTH - 1) {
                                yCoordinate = 0;
                                xCoordinate++;
                            } else {
                                yCoordinate++;
                            }
                        } else {
                            if (xCoordinate == Board.BOARD_LENGTH - 1) {
                                xCoordinate = 0;
                                yCoordinate++;
                            } else {
                                xCoordinate++;
                            }
                        }
                        break;
                }
                placementCode = this.player2.placeShip(orientation, type, xCoordinate, yCoordinate);
            }
        }
        this.state = GameState.P1_SHOOTING;
        return ReturnCode.AI_PLACEMENT_SUCCEEDED;
    }

    public ReturnCode aiShoot() {
        ReturnCode returnCode;
        int xCoordinate;
        int yCoordinate;
        switch (this.shotType) {
            case DETERMINING_SHOT_1:
                if (Math.random() % 2 == 0) {
                    this.hitShipOffset = 1;
                } else {
                    this.hitShipOffset = -1;
                }
                if (Math.random() % 2 == 0) {
                    this.hitShipOrientation = Orientation.HORIZONTAL;
                    xCoordinate = this.hitShipXCoordinate + this.hitShipOffset;
                    yCoordinate = this.hitShipYCoordinate;
                } else {
                    this.hitShipOrientation = Orientation.VERTICAL;
                    xCoordinate = this.hitShipXCoordinate;
                    yCoordinate = this.hitShipYCoordinate + this.hitShipOffset;
                }
                returnCode = player1.shotAt(xCoordinate, yCoordinate);
                if (returnCode == ReturnCode.SHIP_HIT) {
                    if (this.hitShipOffset >= 1) {
                        this.hitShipOffset++;
                    } else {
                        this.hitShipOffset--;
                    }
                    this.shotType = AIShotType.LINE_SHOT;
                } else {
                    this.shotType = AIShotType.DETERMINING_SHOT_2;
                }
                break;
            case DETERMINING_SHOT_2:
                if (this.hitShipOrientation == Orientation.HORIZONTAL) {
                    this.hitShipOrientation = Orientation.VERTICAL;
                    xCoordinate = this.hitShipXCoordinate;
                    yCoordinate = this.hitShipYCoordinate + this.hitShipOffset;
                } else {
                    this.hitShipOrientation = Orientation.HORIZONTAL;
                    xCoordinate = this.hitShipXCoordinate + this.hitShipOffset;
                    yCoordinate = this.hitShipYCoordinate;
                }
                returnCode = player1.shotAt(xCoordinate, yCoordinate);
                if (returnCode == ReturnCode.SHIP_HIT) {
                    if (this.hitShipOffset >= 1) {
                        this.hitShipOffset++;
                    } else {
                        this.hitShipOffset--;
                    }
                    this.shotType = AIShotType.LINE_SHOT;
                } else {
                    this.shotType = AIShotType.DETERMINING_SHOT_3;
                }
                break;
            case DETERMINING_SHOT_3:
                if (this.hitShipOffset == 1) {
                    this.hitShipOffset = -1;
                } else {
                    this.hitShipOffset = 1;
                }
                if (this.hitShipOrientation == Orientation.HORIZONTAL) {
                    xCoordinate = this.hitShipXCoordinate + this.hitShipOffset;
                    yCoordinate = this.hitShipYCoordinate;
                } else {
                    xCoordinate = this.hitShipXCoordinate;
                    yCoordinate = this.hitShipYCoordinate + this.hitShipOffset;
                }
                returnCode = player1.shotAt(xCoordinate, yCoordinate);
                if (returnCode == ReturnCode.SHIP_HIT) {
                    if (this.hitShipOffset >= 1) {
                        this.hitShipOffset++;
                    } else {
                        this.hitShipOffset--;
                    }
                    this.shotType = AIShotType.LINE_SHOT;
                } else {
                    this.shotType = AIShotType.DETERMINING_SHOT_2;
                }
                break;
            case LINE_SHOT:
                if (this.hitShipOrientation == Orientation.HORIZONTAL) {
                    xCoordinate = this.hitShipXCoordinate + this.hitShipOffset;
                    yCoordinate = this.hitShipYCoordinate;
                } else {
                    xCoordinate = this.hitShipXCoordinate;
                    yCoordinate = this.hitShipYCoordinate + this.hitShipOffset;
                }
                returnCode = player1.shotAt(xCoordinate, yCoordinate);
                if (returnCode != ReturnCode.SHIP_HIT) {
                    if (this.hitShipOffset >= 1) {
                        this.hitShipOffset = -1;
                    } else {
                        this.hitShipOffset = 1;
                    }
                } else {
                    if (this.hitShipOffset >= 1) {
                        this.hitShipOffset++;
                    } else {
                        this.hitShipOffset--;
                    }
                }
                break;
            case RANDOM_SHOT:
            default:
                Double xCoordinateDouble = Math.random() * 10;
                Double yCoordinateDouble = Math.random() * 10;
                xCoordinate = xCoordinateDouble.intValue();
                yCoordinate = yCoordinateDouble.intValue();
                returnCode = player1.shotAt(xCoordinate, yCoordinate);
                if (returnCode == ReturnCode.SHIP_HIT) {
                    this.hitShipXCoordinate = xCoordinate;
                    this.hitShipYCoordinate = yCoordinate;
                    this.shotType = AIShotType.DETERMINING_SHOT_1;
                }
        }
        this.player2.incrementShotCount();
        switch (returnCode) {
            case SHIP_SUNK:
                this.player2.incrementHitCount();
                this.shotType = AIShotType.RANDOM_SHOT;
                break;
            case SHIP_HIT:
                this.player2.incrementHitCount();
                break;
            case MISSED:
                this.state = GameState.P1_SHOOTING;
                break;
            case PLAYER_LOST:
                this.player2.incrementHitCount();
                this.winner = this.player2;
                this.state = GameState.GAME_OVER;
                break;
        }
        return returnCode;
    }

    public GameMode getGameMode() {
        return this.mode;
    }

    public Field[][] getActivePlayerFields() {
        Field[][] fields = null;
        switch (this.state) {
            case P1_PLACEMENT:
            case P1_SHOOTING:
                fields = this.player1.getFields();
                break;
            case P2_PLACEMENT:
            case P2_SHOOTING:
                fields = this.player2.getFields();
                break;
        }
        return fields;
    }

    public Field[][] getInactivePlayerFields() {
        Field[][] fields = null;
        switch (this.state) {
            case P1_PLACEMENT:
            case P1_SHOOTING:
                fields = this.player2.getFields();
                break;
            case P2_PLACEMENT:
            case P2_SHOOTING:
                fields = this.player1.getFields();
                break;
        }
        return fields;
    }

    public int getPlayer1ShotCount() {
        return this.player1.getShotCount();
    }

    public int getPlayer2ShotCount() {
        return this.player2.getShotCount();
    }

    public int getPlayer1HitCount() {
        return this.player1.getHitCount();
    }

    public int getPlayer2HitCount() {
        return this.player2.getHitCount();
    }

    public int getPlayer1ShotHitRatio() {
        return this.player1.getShotHitRatio();
    }

    public int getPlayer2ShotHitRatio() {
        return this.player2.getShotHitRatio();
    }

    public ArrayList<Ship> getPlayer1Ships() {
        return this.player1.getShips();
    }

    public ArrayList<Ship> getPlayer2Ships() {
        return this.player2.getShips();
    }

    public ArrayList<ShipType> getUnplacedShipTypes() {
        if (this.state == GameState.P1_PLACEMENT) {
            return this.player1.getUnplacedShipTypes();
        }
        return this.player2.getUnplacedShipTypes();
    }

    public String getActivePlayerName() {
        String name = "";
        switch (this.state) {
            case P1_PLACEMENT:
            case P1_SHOOTING:
                name = this.player1.getName();
                break;
            case P2_PLACEMENT:
            case P2_SHOOTING:
                name = this.player2.getName();
                break;
            case GAME_OVER:
                name = this.winner.getName();
                break;
        }
        return name;
    }
    
    public String getInactivePlayerName() {
        String name = "";
        switch (this.state) {
            case P1_PLACEMENT:
            case P1_SHOOTING:
                name = this.player2.getName();
                break;
            case P2_PLACEMENT:
            case P2_SHOOTING:
                name = this.player1.getName();
                break;
        }
        return name;
    }

}
