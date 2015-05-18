package prog2_group7_battleships;

import javafx.application.Application;
import javafx.stage.Stage;
import prog2_group7_battleships.ctrl.Controller;
import prog2_group7_battleships.ihm.View;
import prog2_group7_battleships.wrk.Game;
import prog2_group7_battleships.wrk.GameMode;

public class GameLauncher extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // todo: set gamemode dynamically
        Game game = new Game(GameMode.SINGLE);
        View view = new View();
        Controller ctrl = new Controller(view, game);
        view.setController(ctrl);

        ctrl.start();
    }

}