package andioopp.view.util;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;

/**
 * Translates coordinates from an outside coordinate-system of the whole window to an inside coordinate-system of a specific part of the window.
 */
public class Viewport {

    private final Dimension insideDimension;
    private final Dimension outsideDimension;
    private final Vector3f outsideOffset;

    public Viewport(Dimension insideDimension, Dimension outsideDimension, Vector3f outsideOffset) {
        this.insideDimension = insideDimension;
        this.outsideDimension = outsideDimension;
        this.outsideOffset = outsideOffset;
    }

    /**
     * Returns a Vector for the outside coordinate-system from the inside coordinate-system.
     */
    public Vector3f getPositionOutside(Vector3f positionInside) {
        return outsideOffset.add(positionInside.scale(getInsideToOutsideScaling()));
    }
    /**
     * Returns a Dimension for the outside coordinate-system from the inside coordinate-system.
     */
    public Dimension getSizeOutside(Dimension sizeInside) {
        return new Dimension(sizeInside.toVector().scale(getInsideToOutsideScaling()));
    }

    private Vector3f getInsideToOutsideScaling() {
        return outsideDimension.toVector().scale(insideDimension.toVector().inverse());
    }
}
