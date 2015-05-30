package prog2_group7_battleships.ihm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import prog2_group7_battleships.ctrl.Controller;
import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.enums.GameState;

public class GUIView implements Viewable {

    private final Stage primaryStage;
    private BorderPane rootLayout;
    private BattlefieldController bfCtrl;
    private Controller ctrl;

    public GUIView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.initRootLayout();
        this.initBattlefield();
    }

    
    private void initBattlefield() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("Battlefield.fxml"));
            AnchorPane battleshipsOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(battleshipsOverview);

            // Give the controller access to the main app.
            this.bfCtrl = loader.getController();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("RootLayout.fxml"));
            this.rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(this.rootLayout);
            primaryStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public void queryPlacement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void queryMode() {
        primaryStage.show();
    }

    public void setMode(GameMode mode) {
    	this.ctrl.setGameMode(mode);
    }
    
    @Override
    public void queryShooting() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void updateView() {
		this.bfCtrl.fillFields();
	}

	
	public void shootShip(int xCoordinate, int yCoordinate ) {
		switch (this.ctrl.getGameState()) {
		case P1_SHOOTING: 
		case P2_SHOOTING:
			this.ctrl.shoot(xCoordinate, yCoordinate);		}
	}


	public void placeShip(int x, int y) {
		switch (this.ctrl.getGameState()) {
		case P1_PLACEMENT: 
		case P2_PLACEMENT:
			//this.ctrl.placeShip(orientation, type, xCoordinate, yCoordinate);
		}
	}
}
