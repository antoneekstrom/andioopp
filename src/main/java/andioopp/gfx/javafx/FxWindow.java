package andioopp.gfx.javafx;

import andioopp.gfx.Renderer;
import andioopp.gfx.Window;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class FxWindow implements Window {

    private final Stage stage;
    private final Canvas canvas;

    public FxWindow(Stage stage, Canvas canvas) {
        this.stage = stage;
        this.canvas = canvas;
    }

    @Override
    public Renderer getRenderer() {
        return new FxRenderer(getCanvas().getGraphicsContext2D());
    }

    private Canvas getCanvas() {
        return canvas;
    }

    private Stage getStage() {
        return stage;
    }
}
