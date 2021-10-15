package andioopp.common.math.rectangle;

import andioopp.common.math.Dimension;
import andioopp.common.math.Rectangle;
import andioopp.common.math.Vector3f;

public class MutableRectangle implements Rectangle {

    private Rectangle rectangle;

    public MutableRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public MutableRectangle(Vector3f position, Dimension size) {
        this(new ImmutableRectangle(position, size));
    }

    public MutableRectangle(float x, float y, float w, float h) {
        this(new Vector3f(x, y), new Dimension(w, h));
    }

    private void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Vector3f getPosition() {
        return rectangle.getPosition();
    }

    @Override
    public MutableRectangle setPosition(Vector3f position) {
        setRectangle(rectangle.setPosition(position));
        return this;
    }

    @Override
    public Dimension getSize() {
        return rectangle.getSize();
    }

    @Override
    public MutableRectangle setSize(Dimension size) {
        setRectangle(rectangle.setSize(size));
        return this;
    }
}
