package andioopp.view.util;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;

/**
 * Translates coordinates from an outside coordinatesystem to an inner coordinatesystem.
 *
 * @author Anton Ekström
 */
public class Viewport {

    private final Dimension insideDimension;
    private final Dimension outsideDimension;
    private final Vector3f outsideOffset;

    /**
     * Creates a viewport.
     *
     * @param insideDimension  the size of the inner coordinate system
     * @param outsideDimension the size of the coordinate system
     * @param outsideOffset    the position of the coordinate system on the outside
     */
    public Viewport(Dimension insideDimension, Dimension outsideDimension, Vector3f outsideOffset) {
        this.insideDimension = insideDimension;
        this.outsideDimension = outsideDimension;
        this.outsideOffset = outsideOffset;
    }

    /**
     * Translates a position from the inner coordinate system to the outer.
     *
     * @param positionInside the position on the inside
     * @return the position on the outside
     */
    public Vector3f getPositionOutside(Vector3f positionInside) {
        return outsideOffset.add(positionInside.scale(getInsideToOutsideScaling()));
    }

    /**
     * Translates a size from the inner coordinate system to the outer.
     *
     * @param sizeInside the size on the inside
     * @return the size on the outside
     */
    public Dimension getSizeOutside(Dimension sizeInside) {
        return new Dimension(sizeInside.toVector().scale(getInsideToOutsideScaling()));
    }

    private Vector3f getInsideToOutsideScaling() {
        return outsideDimension.toVector().scale(insideDimension.toVector().inverse());
    }
}
