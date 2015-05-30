package prog2_group7_battleships;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import prog2_group7_battleships.ctrl.Controller;
import prog2_group7_battleships.ihm.BattlefieldController;
import prog2_group7_battleships.ihm.RootLayoutController;
import prog2_group7_battleships.ihm.View;
import prog2_group7_battleships.wrk.Game;

public class GameLauncher extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private BattlefieldController controller;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Game game = new Game();
        View view = new View();
        initRootLayout(game, view);
        showBattleship(game, view);

        Controller ctrl = new Controller(view, game);
        //view.setBattlefieldController(this.controller);
        view.setController((Controller) ctrl);
        ctrl.start();
    }

    private void showBattleship(Game game, View view) {
        try {
            // Load CD overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GameLauncher.class.getResource("./ihm/Battlefield.fxml"));
            AnchorPane battleshipsOverview = (AnchorPane) loader.load();

            // Set CD overview into the center of root layout.
            rootLayout.setCenter(battleshipsOverview);

            // Give the controller access to the main app.
            this.controller = loader.getController();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimayStage() {
        return this.primaryStage;
    }

    public void initRootLayout(Game game, View view) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GameLauncher.class.getResource("./ihm/RootLayout.fxml"));
            this.rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(this.rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
