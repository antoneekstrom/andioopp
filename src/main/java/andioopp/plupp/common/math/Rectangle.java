package andioopp.plupp.common.math;

import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;

public interface Rectangle<V extends Vector3f> {
    /**
     * Sets the position of the rectangle. May or may not mutate this object.
     *
     * @param position the new position
     * @return the result, may be the same object
     */
    Rectangle<V> setPosition(Vector3f position);

    /**
     * Sets the position of the rectangle. May or may not mutate this object.
     *
     * @param size the new size
     * @return the result, may be the same object
     */
    Rectangle<V> setSize(Dimension size);

    /**
     * Returns the position.
     * @return the position
     */
    V getPosition();

    /**
     * Returns the size.
     * @return the size
     */
    Dimension getSize();
}
