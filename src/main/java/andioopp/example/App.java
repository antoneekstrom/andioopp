package andioopp.example;

import andioopp.gfx.javafx.FxWindowBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Application entrypoint which handles window creation.
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        new ExampleProgram().run(createWindowBuilder(stage).build());
    }

    private FxWindowBuilder createWindowBuilder(Stage stage) {
        FxWindowBuilder builder = new FxWindowBuilder(stage);
        builder.setTitle("Example");
        builder.setSize(new Dimension(1280, 720));
        builder.setResizable(true);
        builder.setIcon("mario_icon.png");
        return builder;
    }

    public static void run(String[] args) {
        launch(args);
    }

}
