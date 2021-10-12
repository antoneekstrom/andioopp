package andioopp.common.transform;

import java.util.Objects;

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

    public static Dimension unit() {
        return new Dimension(Vector3f.one());
    }

    public Vector3f centerWithin(Vector3f position, Dimension other) {
        Vector3f center = getCenter(position);
        Vector3f offset = other.toVector().scale(-0.5f);
        return center.add(offset);
    }

    public Vector3f getCenter(Vector3f position) {
        return position.add(toVector().scale(0.5f));
    }

    public Dimension scaleByWidth(float width) {
        float ratio = getWidth() / getHeight();
        return new Dimension(width, width / ratio);
    }

    public Dimension scaleByHeight(float height) {
        float ratio = getWidth() / getHeight();
        return new Dimension(height * ratio, height);
    }

    public float getWidth() {
        return toVector().getX();
    }

    public float getHeight() {
        return toVector().getY();
    }

    public Vector3f toVector() {
        return dimension;
    }

    public Dimension round() {
        return new Dimension(toVector().round());
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
