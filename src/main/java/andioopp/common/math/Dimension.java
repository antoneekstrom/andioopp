package andioopp.common.math;

import java.util.Objects;

/**
 * Represents a two-dimensional size or dimension.
 */
public class Dimension {

    private final Vector3f dimension;

    public Dimension(Vector3f vector) {
        this.dimension = vector;
    }

    public Dimension(Dimension dimension) {
        this.dimension = dimension.toVector();
    }

    public Dimension(float width, float height) {
        this(new Vector3f(width, height));
    }


    public Vector3f toVector() {
        return dimension;
    }

    public Dimension halved() { return new Dimension(new Vector3f(getWidth() / 2, getHeight() / 2)); }


    /**
     * Sets the width of the dimension while preserving the ratio between width and height.
     * @param width the new width
     * @return the result
     */
    public Dimension setWidth(float width) {
        float ratio = getWidth() / getHeight();
        return new Dimension(new Vector3f(width, width / ratio));
    }

    /**
     * Sets the height of the dimension while preserving the ratio between width and height.
     * @param height the new width
     * @return the result
     */
    public Dimension setHeight(float height) {
        float ratio = getWidth() / getHeight();
        return new Dimension(new Vector3f(height * ratio, height));
    }


    public float getWidth() {
        return toVector().getX();
    }

    public float getHeight() {
        return toVector().getY();
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
