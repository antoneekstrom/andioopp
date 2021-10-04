package andioopp.common.transform;

public class Rectangle {

    private final Vector3f position;
    private final Dimension size;

    public Rectangle(Vector3f position, Dimension size) {
        this.position = position;
        this.size = size;
    }

    public boolean contains(Vector3f point) {
        Vector3f min = getPosition();
        Vector3f max = getPosition().add(getSize().toVector());
        return point.getX() >= min.getX() && point.getX() <= max.getX() && point.getY() >= min.getY() && point.getY() <= max.getY();
    }

    public Vector3f getPosition() {
        return position;
    }

    public Dimension getSize() {
        return size;
    }
}
