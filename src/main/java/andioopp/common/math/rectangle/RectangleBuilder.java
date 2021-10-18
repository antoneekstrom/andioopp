package andioopp.common.math.rectangle;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;

/**
 * Creates rectangles and provides helper methods for aligning and positioning the rectangle.
 *
 * @author Anton Ekström
 */
public class RectangleBuilder {

    private final MutableRectangle rectangle;

    public RectangleBuilder(Vector3f position, Dimension size) {
        this(new ImmutableRectangle(position, size));
    }

    public RectangleBuilder(Rectangle rectangle) {
        this.rectangle = new MutableRectangle(rectangle);
    }

    public RectangleBuilder() {
        this(new ImmutableRectangle(Vector3f.ZERO, Dimension.UNIT));
    }

    public RectangleBuilder alignBottomWithY(float y) {
        rectangle.setBottomRightCorner(rectangle.getBottomRightCorner().setY(y));
        return this;
    }

    public RectangleBuilder alignCenterWithX(float x) {
        rectangle.setCenter(rectangle.getCenter().setX(x));
        return this;
    }

    public RectangleBuilder setInsideOtherWithPadding(Rectangle other, Vector3f padding) {
        rectangle.setPosition(other.getPosition().add(padding));
        rectangle.setSize(new Dimension(other.getSize().toVector().sub(padding.scale(2f))));
        return this;
    }

    public Rectangle build() {
        return new ImmutableRectangle(rectangle);
    }

}
