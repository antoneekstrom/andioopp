package andioopp.gfx;

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

    int getWidth();

    int getHeight();

}
