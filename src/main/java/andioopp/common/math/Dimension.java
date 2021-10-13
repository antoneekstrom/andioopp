package andioopp.common.math;

import java.util.Objects;

/**
 * Represents a two-dimensional size or dimension.
 */
public class Dimension {

    private final Vector3f dimension;

    public Dimension(Dimension dimension) {
        this.dimension = dimension.toVector();
    }

    public Dimension(Vector3f dimension) {
        this.dimension = dimension;
    }

    public Dimension(float width, float height) {
        this(new Vector3f(width, height));
    }

    /**
     * Creates a dimension with each side set to one.
     *
     * @return the dimension
     */
    public static Dimension unit() {
        return new Dimension(Vector3f.one());
    }

    public Vector3f toVector() {
        return dimension;
    }

    public Dimension round() {
        return new Dimension(toVector().round());
    }

    public Dimension halved() { return new Dimension(getWidth() / 2, getHeight() / 2); }


    /**
     * Sets the width of the dimension while preserving the ratio between width and height.
     * @param width the new width
     * @return the result
     */
    public Dimension setWidth(float width) {
        float ratio = getWidth() / getHeight();
        return new Dimension(width, width / ratio);
    }

    /**
     * Sets the height of the dimension while preserving the ratio between width and height.
     * @param height the new width
     * @return the result
     */
    public Dimension setHeight(float height) {
        float ratio = getWidth() / getHeight();
        return new Dimension(height * ratio, height);
    }


    public float getWidth() {
        return toVector().getX();
    }

    public float getHeight() {
        return toVector().getY();
    }


    /**
     * @param position the position
     * @param other    the other
     * @return the result
     * @deprecated
     */
    public Vector3f centerWithin(Vector3f position, Dimension other) {
        Vector3f center = getCenter(position);
        Vector3f offset = other.toVector().scale(-0.5f);
        return center.add(offset);
    }

    /**
     * @param position the position
     * @return the result
     * @deprecated
     */
    public Vector3f getCenter(Vector3f position) {
        return position.add(toVector().scale(0.5f));
    }


    @Override
    public String toString() {
        return "Dimension{" +
                "dimension=" + dimension +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension dimension1 = (Dimension) o;
        return dimension.equals(dimension1.dimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimension);
    }
}
