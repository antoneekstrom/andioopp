package andioopp.common.graphics;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.vector.Vector3f;
import javafx.scene.text.Font;

/**
 * Draws graphics onto its corresponding {@link Window}.
 *
 * @param <S> Describes the type of {@link Sprite}
 * @author Anton Ekström
 */
public interface Renderer<S extends Sprite<?>> {

    /**
     * Rotates the next draw call around the given point.
     *
     * @param origin the point to rotate around
     * @param angle  the amount in radians to rotate by
     */
    void rotate(Vector3f origin, float angle);

    /**
     * Draws a {@link Sprite}.
     *
     * @param sprite   {@link Sprite} to draw
     * @param position where the sprite is to be drawn
     * @param size     how large the sprite is to be drawn
     */
    void drawSprite(S sprite, Vector3f position, Dimension size);

    /**
     * Draws a {@link Sprite}.
     *
     * @param sprite    {@link Sprite} to draw
     * @param rectangle where the sprite is to be drawn and how large to draw it
     */
    default void drawSprite(S sprite, Rectangle rectangle) {
        drawSprite(sprite, rectangle.getPosition(), rectangle.getSize());
    }

    /**
     * Draws a rectangle.
     *
     * @param position   Where the rectangle should be drawn
     * @param dimensions Width and height of the rectangle
     */
    void drawRectangle(Vector3f position, Dimension dimensions, Color color);

    /**
     * Draws a rectangle.
     *
     * @param rectangle the rectangle to draw
     * @param color     the color of the rectangle
     */
    default void drawRectangle(Rectangle rectangle, Color color) {
        drawRectangle(rectangle.getPosition(), rectangle.getSize(), color);
    }

    /**
     * Draws text to the canvas.
     *
     * @param position Where the text should be written
     * @param text     The actual text that should be displayed
     */
    void writeText(Vector3f position, String text, Color color, Font font);

    /**
     * Clears the canvas by filling with a certain color.
     *
     * @param color the color to fill with
     */
    void clear(Color color);

    /**
     * Creates a factory which produces sprites that are compatible with this renderer.
     *
     * @return The {@link SpriteFactory}
     */
    SpriteFactory<S> getSpriteFactory();

}
