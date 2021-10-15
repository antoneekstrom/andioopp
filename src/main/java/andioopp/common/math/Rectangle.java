package andioopp.common.math;

public interface Rectangle {
    /**
     * Sets the position of the rectangle. May or may not mutate this object.
     *
     * @param position the new position
     * @return the result, may be the same object
     */
    Rectangle setPosition(Vector3f position);

    /**
     * Sets the position of the rectangle. May or may not mutate this object.
     *
     * @param size the new size
     * @return the result, may be the same object
     */
    Rectangle setSize(Dimension size);

    /**
     * Returns the position.
     * @return the position
     */
    Vector3f getPosition();

    /**
     * Returns the size.
     * @return the size
     */
    Dimension getSize();
}
