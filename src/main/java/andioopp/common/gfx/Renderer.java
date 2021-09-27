package andioopp.common.gfx;

import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;

/**
 * Draws graphics onto its corresponding {@link Window}.
 * @param <S> Describes the type of {@link Sprite}
 */
public interface Renderer<S extends Sprite<?>> {
    /**
     * Draws a {@link Sprite}.
     * @param sprite {@link Sprite} to draw
     */
    void drawSprite(S sprite, Transform transform, Vector3f size);

    /**
     * 
     * @param sprite
     * @param transform
     */
    default void drawSprite(S sprite, Transform transform) {
        drawSprite(sprite, transform, new Vector3f(sprite.getWidth(), sprite.getHeight()));
    }

    /**
     * Draws a rectangle.
     * @param position Where the rectangle should be drawn
     * @param dimensions Width and height of the rectangle
     */
    void drawRectangle(Vector3f position, Vector3f dimensions, Color color);

    /**
     *
     * @param color
     */
    void clear(Color color);

    /**
     * Creates a factory which produces sprites that are compatible with this renderer.
     * @return The {@link SpriteFactory}
     */
    SpriteFactory<S> getSpriteFactory();
}
