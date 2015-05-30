package prog2_group7_battleships;

import javafx.application.Application;
import javafx.stage.Stage;
import prog2_group7_battleships.ctrl.Controller;
import prog2_group7_battleships.ihm.GUIView;
import prog2_group7_battleships.wrk.Game;

public class GameLauncher extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new Game();
        //ConsoleView view = new ConsoleView();
        GUIView view = new GUIView(primaryStage);

        Controller ctrl = new Controller(view, game);
        view.setController(ctrl);
        ctrl.start();
    }

}
