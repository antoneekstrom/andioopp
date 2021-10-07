package andioopp.main;

import andioopp.common.gfx.javafx.FxWindowBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application entrypoint.
 */
public class App extends Application {
    public static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new Game().run(new FxWindowBuilder(stage));
    }
}
