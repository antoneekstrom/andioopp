package andioopp.gfx;

/**
 * Draws graphics onto its corresponding {@link Window}.
 * @param <S> Describes the type of {@link Sprite}
 */
public interface Renderer<S extends Sprite<?>> {
    /**
     * Draws a {@link Sprite}.
     * @param sprite {@link Sprite} to draw
     */
    void drawSprite(S sprite);

    /**
     * Creates a factory which produces sprites that are compatible with this renderer.
     * @return The {@link SpriteFactory}
     */
    SpriteFactory<S> getSpriteFactory();
}
