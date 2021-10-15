package andioopp.common.graphics;

import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.common.math.transform.ConcreteTransform;
import andioopp.common.math.transform.Transform;
import javafx.scene.text.Font;

/**
 * Draws graphics onto its corresponding {@link Window}.
 * @param <S> Describes the type of {@link Sprite}
 */
public interface Renderer<S extends Sprite<?>> {
    /**
     * Draws a {@link Sprite}.
     * @param sprite {@link Sprite} to draw
     */
    void drawSprite(S sprite, Transform transform, Dimension size);

    /**
     * Draws a sprite.
     * @param sprite the sprite to draw
     * @param position where the sprite should be drawn
     */
    default void drawSprite(S sprite, Vector3f position, Dimension size) {
        drawSprite(sprite, ConcreteTransform.getFactory().createWithPosition(position), size);
    }

    /**
     * Draws a sprite.
     * @param sprite the sprite to draw
     * @param transform how the sprite should be drawn
     */
    default void drawSprite(S sprite, Transform transform) {
        drawSprite(sprite, transform, sprite.getSize());
    }

    /**
     * Draws a rectangle.
     * @param position Where the rectangle should be drawn
     * @param dimensions Width and height of the rectangle
     */
    void drawRectangle(Vector3f position, Dimension dimensions, Color color);

    /**
     * Clears the canvas by filling with a certain color.
     * @param color the color to fill with
     */
    void clear(Color color);

    /**
     * Creates a factory which produces sprites that are compatible with this renderer.
     * @return The {@link SpriteFactory}
     */
    SpriteFactory<S> getSpriteFactory();

    /**
     * Draws text to the canvas.
     * @param position Where the text should be written
     * @param text  The actual text that should be displayed
     */
    void writeText(Vector3f position, String text, Color color, Font font);

}
