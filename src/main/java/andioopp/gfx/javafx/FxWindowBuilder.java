package andioopp.gfx.javafx;

import andioopp.gfx.WindowBuilder;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.awt.*;

public class FxWindowBuilder implements WindowBuilder {

    private final Stage stage;

    private String title;
    private Dimension size;

    public FxWindowBuilder(Stage stage) {
        this.stage = stage;
    }

    @Override
    public FxWindow build() {
        Canvas canvas = createCanvas();
        configureStage(getStage(), createScene(canvas));
        return new FxWindow(getStage(), canvas);
    }

    private void configureStage(Stage stage, Scene scene) {
        stage.setTitle(getTitle());
        stage.setScene(scene);
        stage.show();
    }

    private Canvas createCanvas() {
        return new Canvas(getSize().getWidth(), getSize().getHeight());
    }

    private Scene createScene(Canvas canvas) {
        return new Scene(new Group(canvas));
    }

    private Stage getStage() {
        return stage;
    }

    public Dimension getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
