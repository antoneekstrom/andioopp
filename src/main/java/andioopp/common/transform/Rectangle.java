package andioopp.common.transform;

import java.util.Objects;

public class Rectangle {

    private final Vector3f position;
    private final Dimension size;

    public Rectangle(Vector3f position, Dimension size) {
        this.position = position;
        this.size = size;
    }

    public Rectangle(float x, float y, float w, float h) {
        this(new Vector3f(x, y), new Dimension(w, h));
    }

    public boolean contains(Vector3f point) {
        Vector3f min = getPosition();
        Vector3f max = getPosition().add(getSize().toVector());
        return point.getX() >= min.getX() && point.getX() <= max.getX() && point.getY() >= min.getY() && point.getY() <= max.getY();
    }

    public Rectangle round() {
        return new Rectangle(getPosition().round(), getSize().round());
    }

    public Vector3f getPosition() {
        return position;
    }

    public Dimension getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "position=" + position +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return getPosition().equals(rectangle.getPosition()) && getSize().equals(rectangle.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getSize());
    }
}
