package andioopp.common.gfx.javafx;

import andioopp.common.gfx.Window;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * {@link Window} implementation for JavaFX. Encapsulates a {@link Stage} and {@link Canvas} instance.
 */
public class FxWindow implements Window<FxRenderer> {

    private final Stage stage;
    private final Canvas canvas;

    public FxWindow(Stage stage, Canvas canvas) {
        this.stage = stage;
        this.canvas = canvas;
    }

    @Override
    public void setMaximized(boolean isMaximized) {
        getStage().setMaximized(isMaximized);
    }

    @Override
    public FxRenderer getRenderer() {
        return new FxRenderer(getCanvas().getGraphicsContext2D());
    }

    @Override
    public int getWidth() {
        return (int) stage.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) stage.getHeight();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Stage getStage() {
        return stage;
    }
}
