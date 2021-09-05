package andioopp.gfx.javafx;

import andioopp.gfx.Renderer;
import andioopp.gfx.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class FxRenderer implements Renderer<FxSprite> {

    private final GraphicsContext ctx;

    public FxRenderer(GraphicsContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void drawSprite(FxSprite sprite) {
        Point2D p = sprite.getPosition();
        getCtx().drawImage(sprite.getImage(),  p.getX(), p.getY());
    }

    private GraphicsContext getCtx() {
        return ctx;
    }
}
