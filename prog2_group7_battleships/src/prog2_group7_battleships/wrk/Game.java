package prog2_group7_battleships.wrk;

public class Game {

    private Board board;
    private Player player1;
    private Player player2;
    private Player activePlayer;

    public Game(GameMode mode) {
        this.board = new Board();
        switch (mode) {
            case SINGLE:
                player1 = new Player();
                player2 = new Player();
                break;
            case MULTI_CLIENT:
                break;
            case MULTI_SERVER:
                break;
        }
        activePlayer = player1;
    }

    public boolean placeShip(Orientation orientation, ShipType type, int xCoordinate, int yCoordinate) {
        boolean shipPlaced = false;

        // check if player still has ship available
        try {
            Ship ship = activePlayer.getUnplacedShipByType(type);
            ship.setPlaced(this.board.placeShip(xCoordinate, yCoordinate, orientation, ship));
            if (ship.isPlaced()) {
                shipPlaced = true;
            }
        } catch (NullPointerException ex) {
            shipPlaced = false;
        }

        return shipPlaced;
    }

}
