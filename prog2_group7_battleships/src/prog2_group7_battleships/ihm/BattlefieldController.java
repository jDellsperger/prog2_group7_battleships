package prog2_group7_battleships.ihm;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import prog2_group7_battleships.wrk.Board;
import prog2_group7_battleships.wrk.Field;
import prog2_group7_battleships.wrk.Game;
import prog2_group7_battleships.wrk.Ship;

public class BattlefieldController {

    @FXML
    private GridPane grid;

    private Game game;
    private Board board;

    public BattlefieldController() {
    }

    public void initialize(Game game) {

        // this.grid.setCellValueFactory(cellData ->
        // cellData.getValue().getArtistProperty());
    }

    public void initClass(Game g) {
        this.game = g;
        this.board = this.game.getPlayer1().getBoard();
    }

    public void fillFields() {

        // TODO if board == null, throw exception
        Rectangle rectField;

        Field[][] boardFields = this.board.getFields();
        Field tempField;
        Ship tempShip;

        for (int x = 0; x < this.board.BOARD_LENGTH; x++) {
            for (int y = 0; y < this.board.BOARD_LENGTH; y++) {
                tempField = boardFields[x][y];
                tempShip = tempField.getShip();
                rectField = new Rectangle();

                rectField.setWidth(20.0);
                rectField.setHeight(20.0);
                rectField.setFill(Color.AQUA);

                if (tempField.isShotAt()) {
                    if (tempShip == null) {
                        rectField.setFill(Color.DARKBLUE);
                    } else {
                        rectField.setFill(Color.RED);
                    }
                } else {
                    if (tempShip != null) {
                        switch (tempShip.getType()) {
                            case CARRIER:
                                rectField.setFill(Color.BLACK);
                                break;
                            case BATTLESHIP:
                                rectField.setFill(Color.DARKSLATEGRAY);
                                break;
                            case SUBMARINE:
                                rectField.setFill(Color.DARKGREY);
                                break;
                            case DESTROYER:
                                rectField.setFill(Color.GREY);
                                break;
                            case BOAT:
                                rectField.setFill(Color.LIGHTGRAY);
                                break;
                        }
                    }

                }
                rectField.setVisible(true);
                this.grid.add(rectField, y, x);
            }
        }
        // TODO check if needed
        this.grid.setVisible(true);

    }

}
