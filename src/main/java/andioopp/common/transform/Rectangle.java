package andioopp.common.transform;

import andioopp.common.input.Clickable;

public class Rectangle implements Clickable {

    private final Vector3f position;
    private final Dimension size;

    public Rectangle(Vector3f position, Dimension size) {
        this.position = position;
        this.size = size;
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public Dimension getSize() {
        return size;
    }
}
