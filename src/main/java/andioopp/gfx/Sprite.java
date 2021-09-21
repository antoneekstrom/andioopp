package andioopp.gfx;

import andioopp.common.transform.Vector3f;

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

    default Vector3f getSize() {
        return new Vector3f(getWidth(), getHeight());
    }

}
