package andioopp.example;

import andioopp.gfx.javafx.FxWindowBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

public class Example extends Application {
    @Override
    public void start(Stage stage) {
        FxWindowBuilder builder = new FxWindowBuilder(stage);
        builder.setTitle("Example");
        builder.setSize(new Dimension(256, 256));
        builder.setResizable(true);
        builder.setIcon("mario_icon.png");
        new App().run(builder.build());
    }

    public static void run(String[] args) {
        launch(args);
    }

}
