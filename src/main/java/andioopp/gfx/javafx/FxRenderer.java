package andioopp.gfx.javafx;

import andioopp.gfx.Renderer;
import andioopp.gfx.SpriteFactory;
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

    @Override
    public SpriteFactory<FxSprite> getSpriteFactory() {
        return FxSprite::load;
    }

    private GraphicsContext getCtx() {
        return ctx;
    }
}
