package prog2_group7_battleships.ihm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import prog2_group7_battleships.ctrl.Controller;
import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.wrk.Board;

public class GUIView implements Viewable {

	private Controller ctrl;
    private final Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane battlefieldLayout;
    
    private RootLayoutController rootLayoutController;
    private ModeController modeSelectionController;
    private BattlefieldController bfCtrl;
    private ControlsSidepaneController controlsSidepaneController;
    private StatusSidepaneController statusSidepaneCtrl;

    public GUIView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.initRootLayout();
        this.initBattlefieldLayout();
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
            
            this.rootLayoutController = loader.getController();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initModeLayout() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("Mode.fxml"));
            AnchorPane modeLayout = (AnchorPane) loader.load();

            this.rootLayout.setCenter(modeLayout);

            // Give the controller access to the main app.
            this.modeSelectionController = loader.getController();
            this.modeSelectionController.setView(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initBattlefieldLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("Battlefield.fxml"));
            this.battlefieldLayout = (AnchorPane) loader.load();

            this.rootLayout.setCenter(this.battlefieldLayout);

            this.bfCtrl = loader.getController();
            this.bfCtrl.setView(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initControlsSidepaneLayout() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("ControlsSidepane.fxml"));
            this.controlsSidepaneController = loader.getController();
            AnchorPane controlsSidepaneLayout = (AnchorPane) loader.load();

            this.bfCtrl.setSidepane(controlsSidepaneLayout);
                        
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initStatusSidepaneLayout() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("StatusSidepane.fxml"));
            AnchorPane statusSidepaneLayout = (AnchorPane) loader.load();

            this.bfCtrl.setSidepane(statusSidepaneLayout);

            this.statusSidepaneCtrl = loader.getController();
            
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
        this.initBattlefieldLayout();
        this.initControlsSidepaneLayout();
        Board tempBoard = new Board(); // Create a empty board
        this.bfCtrl.fillFields(tempBoard.getFields(), tempBoard.getFields());
    }

    @Override
    public void displayMessage(String message) {
        this.rootLayoutController.setStatusMessage(message);
    }

    @Override
    public void queryMode() {
    	this.initModeLayout();
        this.primaryStage.show();
    }

    @Override
    public void queryShooting() {
        this.initStatusSidepaneLayout();
    }

	@Override
	public void updateView() {
		switch (this.ctrl.getGameState()) {
		case P1_PLACEMENT:
		case P2_PLACEMENT:
			this.bfCtrl.fillFields(this.ctrl.getActivePlayerFields());
			break;
		case P1_SHOOTING:
		case P2_SHOOTING:
			this.bfCtrl.fillFields(this.ctrl.getActivePlayerFields(), this.ctrl.getInactivePlayerFields());
			break;
		default:
			break;
		}
		
	}

	public void setMode(GameMode mode) {
    	this.ctrl.setGameMode(mode);
    }
	
	public void shootShip(int xCoordinate, int yCoordinate) {
		switch (this.ctrl.getGameState()) {
		case P1_SHOOTING:
		case P2_SHOOTING:
			this.ctrl.shoot(xCoordinate, yCoordinate);
			break;
		default:
			break;
		}
	}


	public void placeShip(int x, int y) {
		switch (this.ctrl.getGameState()) {
		case P1_PLACEMENT: 
		case P2_PLACEMENT:
			//TODO
			//this.ctrl.placeShip(orientation, type, xCoordinate, yCoordinate);
			break;
		default:
			break;
		}
	}
}
