package andioopp.view;

import andioopp.common.math.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.Vector3f;

/**
 * Translates coordinates between a defined viewport and the resolution of the viewport.
 * @author Anton Ekström
 */
public class Viewport {

    private final Dimension resolution;
    private final Rectangle viewport;

    /**
     * @param viewport the viewport
     * @param resolution resolution of the viewport
     */
    public Viewport(Rectangle viewport, Dimension resolution) {
        this.resolution = resolution;
        this.viewport = viewport;
    }

    /**
     * Scales a dimension from resolution to viewport size.
     * @param size the dimension
     * @return the scaled dimension
     */
    public Dimension toViewportSize(Dimension size) {
        return new Dimension(size.toVector().scale(getScaling()));
    }

    /**
     * Scales a position from resolution to viewport size.
     * @param position the position
     * @return the scaled position
     */
    public Vector3f toViewportPosition(Vector3f position) {
        return viewport.getPosition().add(position.scale(getScaling()));
    }

    /**
     * Scales a rectangles position and size from resolution to viewport size.
     * @param rectangle the rectangle
     * @return the scaled rectangle
     */
    public Rectangle toViewportRect(Rectangle rectangle) {
        Vector3f position = toViewportPosition(rectangle.getPosition());
        Dimension size = toViewportSize(rectangle.getSize());
        return new ImmutableRectangle(position, size);
    }

    /**
     * @return resolution of the viewport
     */
    public Dimension getResolution() {
        return resolution;
    }

    /**
     * @return the viewport
     */
    public Rectangle getViewport() {
        return viewport;
    }

    private Vector3f getScaling() {
        return viewport.getSize().toVector().scale(resolution.toVector().inverse());
    }
}
