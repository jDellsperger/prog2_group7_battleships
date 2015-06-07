package prog2_group7_battleships.ihm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverLayoutController {

    @FXML
    private Label gameOverTitle;
    @FXML
    private Label player1Shots;
    @FXML
    private Label player1Hits;
    @FXML
    private Label player1Ratio;
    @FXML
    private Label player2Shots;
    @FXML
    private Label player2Hits;
    @FXML
    private Label player2Ratio;
    
    private GUIView view;

    public void setView(GUIView view) {
        this.view = view;
    }
    
    public void setStatistics() {
        this.gameOverTitle.setText(this.view.getActivePlayerName() + " won the Game!");
        this.player1Shots.setText(String.valueOf(this.view.getPlayer1ShotCount()));
        this.player1Hits.setText(String.valueOf(this.view.getPlayer1HitCount()));
        this.player1Ratio.setText(String.valueOf(this.view.getPlayer1ShotHitRatio()) + "%");
        this.player2Shots.setText(String.valueOf(this.view.getPlayer2ShotCount()));
        this.player2Hits.setText(String.valueOf(this.view.getPlayer2HitCount()));
        this.player2Ratio.setText(String.valueOf(this.view.getPlayer2ShotHitRatio()) + "%");
    }

    @FXML
    private void startNewGame() {
        this.view.startNewGame();
    }

}
