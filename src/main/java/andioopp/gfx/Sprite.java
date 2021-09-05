package andioopp.gfx;

import javafx.geometry.Point2D;

/**
 * Image which can be rendered onto a {@link Window}.
 * Defines an image and at which position it should be rendered.
 *
 * @param <T> Describes the type of image
 */
public interface Sprite<T> {
    /**
     * @return Image to be rendered
     */
    T getImage();

    /**
     * @return Where the {@link Sprite} is positioned
     */
    Point2D getPosition();
}
