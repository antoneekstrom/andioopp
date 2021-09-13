package andioopp.gfx;

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
    void drawSprite(S sprite, Transform transform);

    /**
     * Draws a rectangle.
     * @param position Where the rectangle should be drawn
     * @param dimensions Width and height of the rectangle
     */
    void drawRectangle(Vector3f position, Vector3f dimensions);

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
