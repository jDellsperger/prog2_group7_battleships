package prog2_group7_battleships.ihm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import prog2_group7_battleships.wrk.Ship;

public class StatusSidepaneController {

    @FXML
    private Label player1Shots;
    @FXML
    private Label player1Hits;
    @FXML
    private Label player1Ratio;
    @FXML
    private GridPane player1ShipList;
    @FXML
    private Label player2Shots;
    @FXML
    private Label player2Hits;
    @FXML
    private Label player2Ratio;
    @FXML
    private GridPane player2ShipList;
    
    private GUIView view;
    
    public void setView(GUIView view) {
        this.view = view;
    }
    
    public void setStatistics(){
        this.player1Shots.setText(String.valueOf(this.view.getPlayer1ShotCount()));
        this.player1Hits.setText(String.valueOf(this.view.getPlayer1HitCount()));
        this.player1Ratio.setText(String.valueOf(this.view.getPlayer1ShotHitRatio()) + "%");
        this.player1ShipList.getChildren().clear();
        int rowIndex = 1;
        for (Ship player1Ship : this.view.getPlayer1Ships()) {
            Label label = new Label(player1Ship.getType().toString());
            if(player1Ship.isSunk()) {
                label.setStyle("-fx-text-fill: red");
            } else {
                label.setStyle("-fx-text-fill: green");
            }
            this.player1ShipList.add(label, 1, rowIndex);
            rowIndex++;
        }
        this.player2Shots.setText(String.valueOf(this.view.getPlayer2ShotCount()));
        this.player2Hits.setText(String.valueOf(this.view.getPlayer2HitCount()));
        this.player2Ratio.setText(String.valueOf(this.view.getPlayer2ShotHitRatio()) + "%");
        this.player2ShipList.getChildren().clear();
        rowIndex = 1;
        for (Ship player2Ship : this.view.getPlayer2Ships()) {
            Label label = new Label(player2Ship.getType().toString());
            if(player2Ship.isSunk()) {
                label.setStyle("-fx-text-fill: red");
            } else {
                label.setStyle("-fx-text-fill: green");
            }
            this.player2ShipList.add(label, 1, rowIndex);
            rowIndex++;
        }
    }
    
}
