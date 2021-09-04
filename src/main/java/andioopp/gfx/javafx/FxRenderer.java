package andioopp.gfx.javafx;

import andioopp.gfx.Renderer;
import javafx.scene.canvas.GraphicsContext;

public class FxRenderer implements Renderer {

    private final GraphicsContext ctx;

    public FxRenderer(GraphicsContext ctx) {
        this.ctx = ctx;
    }
}
