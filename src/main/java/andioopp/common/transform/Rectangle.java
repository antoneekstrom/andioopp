package andioopp.common.transform;

public interface Rectangle {

    Vector3f getPosition();

    Rectangle setPosition(Vector3f position);

    Dimension getSize();

    Rectangle setSize(Dimension size);

    default Rectangle set(Vector3f position, Dimension size) {
        return setPosition(position).setSize(size);
    }

    default boolean contains(Vector3f point) {
        Vector3f min = getPosition();
        Vector3f max = getPosition().add(getSize().toVector());
        return point.getX() >= min.getX() && point.getX() <= max.getX() && point.getY() >= min.getY() && point.getY() <= max.getY();
    }

    default Rectangle round() {
        return set(getPosition().round(), getSize().round());
    }

}
