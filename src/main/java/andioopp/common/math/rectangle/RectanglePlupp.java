package andioopp.common.math.rectangle;

import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;

/**
 * Represents a two-dimensional rectangle with a position and size.
 */
public interface RectanglePlupp {

    /**
     * Sets the position of the rectangle. May or may not mutate this object.
     *
     * @param position the new position
     * @return the result, may be the same object
     */
    RectanglePlupp setPosition(Vector3f position);

    /**
     * Sets the position of the rectangle. May or may not mutate this object.
     *
     * @param size the new size
     * @return the result, may be the same object
     */
    RectanglePlupp setSize(Dimension size);

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

    /**
     * Centers another rectangle within this rectangle.
     * @param other the other rectangle
     * @return the centered rectangle
     */
    default RectanglePlupp centerWithin(RectanglePlupp other) {
        return other.setPosition(getCenter().sub(other.getSize().halved().toVector()));
    }

    /**
     * Returns the center of the rectangle.
     * @return the center
     */
    default Vector3f getCenter() {
        return getPosition().add(getSize().halved().toVector());
    }

    /**
     * Sets the position and size of the rectangle. May or may not mutate this object.
     *
     * @param position the new position
     * @param size the new size
     * @return the result, may be the same object
     */
    default RectanglePlupp set(Vector3f position, Dimension size) {
        return setPosition(position).setSize(size);
    }

    /**
     * Returns true if the given point is contained within the bounds of the rectangle.
     * @param point the point
     * @return if it is within the bounds of the rectangle
     */
    default boolean contains(Vector3f point) {
        Vector3f min = getPosition();
        Vector3f max = getPosition().add(getSize().toVector());
        return point.getX() >= min.getX() && point.getX() <= max.getX() && point.getY() >= min.getY() && point.getY() <= max.getY();
    }

    /**
     * Rounds each value on the rectangle.
     * @return the result
     */
    default RectanglePlupp round() {
        return set(getPosition().round(), getSize().round());
    }

}
