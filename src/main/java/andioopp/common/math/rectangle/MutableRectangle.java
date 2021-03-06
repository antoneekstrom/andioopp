package andioopp.common.math.rectangle;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;

/**
 * A {@link Rectangle} which is mutable
 */
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
    public MutableRectangle setPosition(Vector3f position) {
        setRectangle(rectangle.setPosition(position));
        return this;
    }

    @Override
    public MutableRectangle setSize(Dimension size) {
        setRectangle(rectangle.setSize(size));
        return this;
    }

    @Override
    public Vector3f getPosition() {
        return rectangle.getPosition();
    }

    @Override
    public Dimension getSize() {
        return rectangle.getSize();
    }
}
