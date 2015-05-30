package prog2_group7_battleships.ihm;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
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
	
	private GUIView view;
	
	@FXML
	private ComboBox shipTypeSelectionBox;
	
	@FXML
	private RadioButton horizonalRadio;
	@FXML
	private RadioButton verticalRadio;
	
	private ToggleGroup orientationRadioGroup;
	
	
	public void initialize() {
		this.orientationRadioGroup = new ToggleGroup();
	}
	
	public void setView (GUIView view) {
		this.view = view;
	}
	
	
	public void fillFields(Field[][] boardFields) {
		
        Rectangle rectField;

        Field tempField;
        Ship tempShip;
        

        for (int x = 0; x < Board.BOARD_LENGTH; x++) {
            for (int y = 0; y < Board.BOARD_LENGTH; y++) {
                tempField = boardFields[x][y];
                tempShip = tempField.getShip();
                rectField = new Rectangle();

                rectField.setWidth(20.0);
                rectField.setHeight(20.0);
                rectField.setFill(Color.AQUA);
                rectField.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
                

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
                this.gridPlayer.add(rectField, y, x);
                
                EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        			@Override
        			public void handle(MouseEvent event) {
        				int x = GridPane.getColumnIndex(rectField);
        				int y = GridPane.getRowIndex(rectField);
        				handlePlaceShip(x,y);
        			}
        		};
            }
        }
	}

	public void fillFields(Field[][] playerFields, Field[][] opponentFields) {
		
	}

	public void handlePlaceShip(int x, int y) {
		this.view.placeShip(x, y);
		
	}
	
	public void handleShootShip() {
		this.view.shootShip();
	}
}
