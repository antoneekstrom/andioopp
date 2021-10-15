package andioopp.main;

import andioopp.common.javafx.graphics.FxWindowBuilder;
import andioopp.game.MarioGame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application entrypoint.
 * @author Anton Ekström
 */
public class App extends Application {

    public static void run(String[] args) {
        launch(args);
    }

    private void run(Stage stage) {
        FxWindowBuilder windowBuilder = new FxWindowBuilder(stage);
        MarioGame game = new MarioGame();
    }

    @Override
    public void start(Stage stage) {
        run(stage);
    }
}
