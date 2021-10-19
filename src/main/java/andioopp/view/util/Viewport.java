package andioopp.view.util;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;

/**
 * Translates coordinates from an outside coordinatesystem to an inside coordinatesystem.
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

    public Vector3f getPositionOutside(Vector3f positionInside) {
        return outsideOffset.add(positionInside.scale(getInsideToOutsideScaling()));
    }

    public Dimension getSizeOutside(Dimension sizeInside) {
        return new Dimension(sizeInside.toVector().scale(getInsideToOutsideScaling()));
    }

    private Vector3f getInsideToOutsideScaling() {
        return outsideDimension.toVector().scale(insideDimension.toVector().inverse());
    }
}
