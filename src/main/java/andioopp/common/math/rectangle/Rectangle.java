package andioopp.common.math.rectangle;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;

/**
 * Represents a rectangle which has a position and size.
 * <p>
 * The position is the point in the top-left corner of the rectangle.
 * The size is the width and height which extends from the position
 * downwards and to the right, forming a rectangle.
 *
 * @author Anton Ekström
 */
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
     *
     * @return the position
     */
    Vector3f getPosition();

    /**
     * Returns the size.
     *
     * @return the size
     */
    Dimension getSize();

    default Rectangle setCenter(Vector3f position) {
        return setPosition(position.sub(getSize().halved().toVector()));
    }

    default Rectangle setBottomRightCorner(Vector3f position) {
        return setPosition(position.sub(getSize().toVector()));
    }

    /**
     * Returns the bottom-right corner of the rectangle, which is the point with the largest value in the rectangle.
     *
     * @return the bottom-right corner
     */
    default Vector3f getBottomRightCorner() {
        return getPosition().add(getSize().toVector());
    }

    /**
     * Translates the position of the rectangle.
     *
     * @param translation the translation to apply
     * @return the translated rectangle
     */
    default Rectangle translate(Vector3f translation) {
        return setPosition(getPosition().add(translation));
    }

    /**
     * Centers another rectangle within this rectangle.
     *
     * @param other the other rectangle
     * @return the centered rectangle
     */
    default Rectangle centerWithin(Rectangle other) {
        return other.setPosition(getCenter().sub(other.getSize().halved().toVector()));
    }

    /**
     * Returns the center of the rectangle.
     *
     * @return the center
     */
    default Vector3f getCenter() {
        return getPosition().add(getSize().halved().toVector());
    }

    /**
     * Sets the position and size of the rectangle. May or may not mutate this object.
     *
     * @param position the new position
     * @param size     the new size
     * @return the result, may be the same object
     */
    default Rectangle set(Vector3f position, Dimension size) {
        return setPosition(position).setSize(size);
    }

    /**
     * Returns true if the given point is contained within the bounds of the rectangle.
     *
     * @param point the point
     * @return if it is within the bounds of the rectangle
     */
    default boolean contains(Vector3f point) {
        Vector3f min = getPosition();
        Vector3f max = getPosition().add(getSize().toVector());
        return point.getX() >= min.getX() && point.getX() <= max.getX() && point.getY() >= min.getY() && point.getY() <= max.getY();
    }

    /**
     * Returns true if two rectangles have any points in common which they contain.
     *
     * @param other the other rectangle
     * @return true if the rectangles intersect
     */
    default boolean intersects(Rectangle other) {
        if (other == null) {
            return false;
        }
        if (this.equals(other)) {
            return true;
        }
        if (getPosition().equals(other.getPosition())) {
            return true;
        }

        Vector3f a = getPosition();
        Vector3f aMax = getBottomRightCorner();
        Vector3f b = other.getPosition();
        Vector3f bMax = other.getBottomRightCorner();

        return a.getX() < bMax.getX()
                && aMax.getX() > b.getX()
                && a.getY() < bMax.getY()
                && aMax.getY() > b.getY();
    }

    /**
     * Rounds each value on the rectangle.
     *
     * @return the result
     */
    default Rectangle round() {
        return set(getPosition().round(), new Dimension(getSize().toVector().round()));
    }
}
