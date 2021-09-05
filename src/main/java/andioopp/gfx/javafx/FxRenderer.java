package andioopp.gfx.javafx;

import andioopp.gfx.Renderer;
import andioopp.gfx.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FxRenderer implements Renderer<Sprite<? extends Image>> {

    private final GraphicsContext ctx;

    public FxRenderer(GraphicsContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void drawSprite(Sprite<? extends Image> sprite) {
        Point2D p = sprite.getPosition();
        getCtx().drawImage(sprite.getImage(),  p.getX(), p.getY());
    }

    private GraphicsContext getCtx() {
        return ctx;
    }
}
