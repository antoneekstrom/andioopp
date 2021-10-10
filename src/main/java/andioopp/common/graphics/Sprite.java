package andioopp.common.graphics;

import andioopp.common.transform.Dimension;

/**
 * Image which can be rendered onto a {@link Window}.
 *
 * @param <T> Describes the type of image
 */
public interface Sprite<T> {
    /**
     * @return Image to be rendered
     */
    T getImage();

    /**
     * Returns the width of the image in pixels
     * @return the width
     */
    int getWidth();

    /**
     * Returns the height of the image in pixels
     * @return the height
     */
    int getHeight();

    /**
     * Returns the size of the image.
     * @return the size
     */
    default Dimension getSize() {
        return new Dimension(getWidth(), getHeight());
    }

}
