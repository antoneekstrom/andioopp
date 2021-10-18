package andioopp.common.javafx.graphics;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.graphics.SpriteFactory;
import andioopp.common.graphics.CachedSpriteFactory;
import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

/**
 * {@link Renderer} implementation for JavaFX.
 */
public class FxRenderer implements Renderer<FxSprite> {

    private final GraphicsContext ctx;
    private final SpriteFactory<FxSprite> spriteFactory;

    public FxRenderer(GraphicsContext ctx) {
        this.ctx = ctx;
        spriteFactory = new CachedSpriteFactory<>(FxSprite::new);
    }

    @Override
    public void drawSprite(FxSprite sprite, Vector3f position, Dimension size) {
        getCtx().drawImage(sprite.getImage(), position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    @Override
    public void drawRectangle(Vector3f position, Dimension dimensions, Color color) {
        getCtx().setFill(getFxColor(color));
        getCtx().fillRect(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight());
    }

    @Override
    public void clear(Color color) {
        getCtx().setFill(getFxColor(color));
        getCtx().fillRect(0, 0, getCtx().getCanvas().getWidth(), getCtx().getCanvas().getHeight());
    }

    @Override
    public void writeText(Vector3f position, String text, Color color, Font font){
        getCtx().setFill(getFxColor(color));
        getCtx().setFont(font);
        getCtx().fillText(text, position.getX(), position.getY());
    }

    @Override
    public SpriteFactory<FxSprite> getSpriteFactory() {
        return spriteFactory;
    }

    private GraphicsContext getCtx() {
        return ctx;
    }

    private javafx.scene.paint.Color getFxColor(Color color) {
        return new javafx.scene.paint.Color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha());
    }

}
