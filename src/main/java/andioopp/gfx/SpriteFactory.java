package andioopp.gfx;

import javafx.geometry.Point2D;

/**
 * Creates sprites.
 * @param <T> Describes the type of {@link Sprite}
 */
@FunctionalInterface
public interface SpriteFactory<T extends Sprite<?>> {
    /**
     * Creates a {@link Sprite}.
     * @param path Location of an image file
     * @param position Where the {@link Sprite} is positioned
     * @return The {@link Sprite}
     */
    T create(String path, Point2D position);
}
