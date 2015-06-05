package prog2_group7_battleships.ihm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import prog2_group7_battleships.ctrl.Controller;
import prog2_group7_battleships.enums.GameMode;

public class GUIView implements Viewable {

    private Controller ctrl;
    private final Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane modeSelect;
    private AnchorPane battlefield;
    private AnchorPane placementControls;
    private AnchorPane statusSidepane;
    private AnchorPane swtichPlayerLayout;
    private AnchorPane gameOverLayout;

    private BattlefieldController bfCtrl;
    private ModeController modeSelectionCtrl;
    private ControlsSidepaneController placementControlsCtrl;
    private RootLayoutController rootLayoutController;
    private StatusSidepaneController statusSidepaneCtrl;
    private SwitchPlayerLayoutController switchLayoutCtrl;
    private GameOverLayoutController GameOverCtrl;

    public GUIView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.initRootLayout();
        this.initMode();
        this.initBattlefield();
        this.initPlacementControls();
        this.initStatusSidepaneLayout();
        this.initSwitchPlayerLayout();
        this.initGameOverLayout();
    }

    private void initMode() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("Mode.fxml"));
            this.modeSelect = (AnchorPane) loader.load();
            this.modeSelectionCtrl = loader.getController();
            this.modeSelectionCtrl.setView(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBattlefield() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("Battlefield.fxml"));
            this.battlefield = (AnchorPane) loader.load();
            this.bfCtrl = loader.getController();
            this.bfCtrl.setView(this);
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

            this.rootLayoutController = loader.getController();
            this.rootLayoutController.setView(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPlacementControls() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("ControlsSidepane.fxml"));
            this.placementControls = (AnchorPane) loader.load();
            this.placementControlsCtrl = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initStatusSidepaneLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("StatusSidepane.fxml"));
            this.statusSidepane = (AnchorPane) loader.load();
            this.statusSidepaneCtrl = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSwitchPlayerLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("SwtichPlayerLayout.fxml"));
            this.swtichPlayerLayout = (AnchorPane) loader.load();
            this.switchLayoutCtrl = loader.getController();
            this.switchLayoutCtrl.setView(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGameOverLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIView.class.getResource("GameOverLayout.fxml"));
            this.gameOverLayout = (AnchorPane) loader.load();
            this.GameOverCtrl = loader.getController();
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
        this.rootLayout.setRight(this.placementControls);
        this.bfCtrl.fillFields(this.ctrl.getActivePlayerFields());
        this.rootLayout.setCenter(this.battlefield);
    }

    @Override
    public void displayMessage(String message) {
        this.rootLayoutController.setStatusMessage(message);
    }

    @Override
    public void queryMode() {
        this.rootLayout.setCenter(this.modeSelect);
        this.primaryStage.show();
    }

    public void setMode(GameMode mode) {
        this.ctrl.setGameMode(mode);
    }

    @Override
    public void queryShooting() {
        this.rootLayout.setRight(this.statusSidepane);
        this.bfCtrl.fillFields(this.ctrl.getActivePlayerFields(), this.ctrl.getInactivePlayerFields());
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
        }
    }

    public void shootShip(int xCoordinate, int yCoordinate) {
        switch (this.ctrl.getGameState()) {
            case P1_SHOOTING:
            case P2_SHOOTING:
                this.ctrl.shoot(xCoordinate, yCoordinate);
        }
    }

    public void placeShip(int x, int y) {
        switch (this.ctrl.getGameState()) {
            case P1_PLACEMENT:
            case P2_PLACEMENT:
                this.ctrl.placeShip(this.placementControlsCtrl.getOrientation(), this.placementControlsCtrl.getAndResetShipType(), x, y);
        }
    }

    public void startNewGame() {
        this.ctrl.startNewGame();
    }

    @Override
    public void displayGameOver() {
        this.rootLayout.getChildren().clear();
        this.rootLayout.setCenter(this.gameOverLayout);
    }

    @Override
    public void switchUser() {
        this.rootLayout.getChildren().clear();
        this.rootLayout.setCenter(this.swtichPlayerLayout);
        this.switchLayoutCtrl.waitForUserConfirmation();
        this.rootLayout.getChildren().clear();
        this.rootLayout.setCenter(this.battlefield);
        this.rootLayout.setRight(this.statusSidepane);
    }

}
