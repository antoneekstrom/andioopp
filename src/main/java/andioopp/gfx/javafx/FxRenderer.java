package andioopp.gfx.javafx;

import andioopp.common.Transform;
import andioopp.common.Vector3f;
import andioopp.gfx.Renderer;
import andioopp.gfx.SpriteFactory;
import javafx.scene.canvas.GraphicsContext;

/**
 * {@link Renderer} implementation for JavaFX.
 */
public class FxRenderer implements Renderer<FxSprite> {

    private final GraphicsContext ctx;

    public FxRenderer(GraphicsContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void drawSprite(FxSprite sprite, Transform transform) {
        Vector3f position = transform.getPosition();
        getCtx().drawImage(sprite.getImage(),  position.getX(), position.getY());
    }

    @Override
    public SpriteFactory<FxSprite> getSpriteFactory() {
        return FxSprite::load;
    }

    private GraphicsContext getCtx() {
        return ctx;
    }
}
