package prog2_group7_battleships.ihm;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import prog2_group7_battleships.wrk.Board;
import prog2_group7_battleships.wrk.Field;
import prog2_group7_battleships.wrk.Ship;

public class BattlefieldController {

    @FXML
    private GridPane gridPlayer;
    @FXML
    private GridPane gridOpponent;
    @FXML
    private Label playerGridLabel;
    @FXML
    private Label opponentGridLabel;

    @FXML
    private Label battlefieldTitle;

    private GUIView view;

    @FXML
    private AnchorPane sidePane;

    public void setView(GUIView view) {
        this.view = view;
    }

    public void fillFields(Field[][] playerFields) {
        Field tempField;
        Ship tempShip;
        this.gridOpponent.setVisible(false);
        this.opponentGridLabel.setVisible(false);
        this.playerGridLabel.setText(this.view.getActivePlayerName() + " Board");

        for (int x = 0; x < Board.BOARD_LENGTH; x++) {
            for (int y = 0; y < Board.BOARD_LENGTH; y++) {
                final Rectangle rectangleField;
                tempField = playerFields[x][y];
                tempShip = tempField.getShip();
                rectangleField = new Rectangle();

                rectangleField.setWidth(20.0);
                rectangleField.setHeight(20.0);
                rectangleField.setFill(Color.AQUA);
                rectangleField.setStroke(Color.BLACK);

                if (tempField.isShotAt()) {
                    if (tempShip == null) {
                        rectangleField.setFill(Color.DARKBLUE);
                    } else {
                        rectangleField.setFill(Color.RED);
                    }
                } else {
                    if (tempShip != null) {
                        switch (tempShip.getType()) {
                            case CARRIER:
                                rectangleField.setFill(Color.BLACK);
                                break;
                            case BATTLESHIP:
                                rectangleField.setFill(Color.DARKSLATEGRAY);
                                break;
                            case SUBMARINE:
                                rectangleField.setFill(Color.DARKGREY);
                                break;
                            case DESTROYER:
                                rectangleField.setFill(Color.GREY);
                                break;
                            case BOAT:
                                rectangleField.setFill(Color.LIGHTGRAY);
                                break;
                        }
                    }

                }
                rectangleField.setVisible(true);
                this.gridPlayer.add(rectangleField, x, y);

                EventHandler<MouseEvent> handler = (MouseEvent event) -> {
                    int x1 = GridPane.getColumnIndex(rectangleField);
                    int y1 = GridPane.getRowIndex(rectangleField);
                    handlePlaceShip(x1, y1);
                };
                rectangleField.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
            }
        }
    }

    public void fillFields(Field[][] playerFields, Field[][] opponentFields) {
        fillFields(playerFields);
        this.gridOpponent.setVisible(true);
        this.opponentGridLabel.setVisible(true);
        this.opponentGridLabel.setText(this.view.getInactivePlayerName() + " Board");

        Field tempField;
        Ship tempShip;

        for (int x = 0; x < Board.BOARD_LENGTH; x++) {
            for (int y = 0; y < Board.BOARD_LENGTH; y++) {
                final Rectangle rectangleField;
                tempField = opponentFields[x][y];
                tempShip = tempField.getShip();
                rectangleField = new Rectangle();

                rectangleField.setWidth(20.0);
                rectangleField.setHeight(20.0);
                rectangleField.setFill(Color.AQUA);
                rectangleField.setStroke(Color.BLACK);

                if (tempField.isShotAt()) {
                    if (tempShip == null) {
                        rectangleField.setFill(Color.DARKBLUE);
                    } else {
                        rectangleField.setFill(Color.RED);
                    }
                }

                rectangleField.setVisible(true);
                this.gridOpponent.add(rectangleField, x, y);

                EventHandler<MouseEvent> handler = (MouseEvent event) -> {
                    int x1 = GridPane.getColumnIndex(rectangleField);
                    int y1 = GridPane.getRowIndex(rectangleField);
                    handleShootShip(x1, y1);
                };

                rectangleField.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
            }
        }
    }

    public void handlePlaceShip(int x, int y) {
        this.view.placeShip(x, y);
    }

    public void handleShootShip(int x, int y) {
        this.view.shootShip(x, y);
    }

    public AnchorPane getSidepane() {
        return this.sidePane;
    }

    public void setSidepane(AnchorPane sidePane) {
        this.sidePane = sidePane;
    }

    public void setBattlefieldTitle(String message) {
        this.battlefieldTitle.setText(message);
    }
}
