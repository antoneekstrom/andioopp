package andioopp.common.gfx.javafx;

import andioopp.common.gfx.SpriteFactory;
import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;
import andioopp.common.gfx.CachedSpriteFactory;
import andioopp.common.gfx.Color;
import andioopp.common.gfx.Renderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.Vector;

/**
 * {@link Renderer} implementation for JavaFX.
 */
public class FxRenderer implements Renderer<FxSprite> {

    private final GraphicsContext ctx;

    public FxRenderer(GraphicsContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void drawRectangle(Vector3f position, Vector3f dimensions, Color color) {
        getCtx().setFill(getFxColor(color));
        getCtx().fillRect(position.getX(), position.getY(), dimensions.getX(), dimensions.getY());
    }

    @Override
    public void drawSprite(FxSprite sprite, Transform transform, Vector3f size) {
        Vector3f position = transform.getPosition();
        getCtx().drawImage(sprite.getImage(),  position.getX(), position.getY(), size.getX(), size.getY());
    }

    @Override
    public void clear(Color color) {
        getCtx().setFill(getFxColor(color));
        getCtx().fillRect(0, 0, getCtx().getCanvas().getWidth(), getCtx().getCanvas().getHeight());
    }

    @Override
    public SpriteFactory<FxSprite> getSpriteFactory() {
        return new CachedSpriteFactory<>(FxSprite::new);
    }

    private GraphicsContext getCtx() {
        return ctx;
    }

    private javafx.scene.paint.Color getFxColor(Color color) {
        return new javafx.scene.paint.Color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha());
    }

    @Override
    public void writeText(Vector3f position, String text){
        getCtx().fillText(text, position.getX(), position.getY());
    }

}
