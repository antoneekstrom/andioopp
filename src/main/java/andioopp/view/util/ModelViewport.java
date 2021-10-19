package andioopp.view.util;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;
import andioopp.model.util.ModelCoordinate;
import andioopp.model.Model;


/**
 * Specifically translates model coordinates to window coordinates.
 */
public class ModelViewport {

    private final Viewport viewport;

    public ModelViewport(Model model, Dimension outsideDimension, Vector3f outsideOffset) {
        viewport = new Viewport(getInsideDimensionFromModel(model), outsideDimension, outsideOffset);
    }

    public Dimension getCellSize() {
        return getSize(Dimension.UNIT);
    }

    public ViewCoordinate getPosition(ModelCoordinate modelCoordinate) {
        return new ViewCoordinate(viewport.getPositionOutside(modelCoordinate));
    }

    public Dimension getSize(Dimension modelSize) {
        return new Dimension(viewport.getSizeOutside(modelSize));
    }

    private Dimension getInsideDimensionFromModel(Model model) {
        return model.getWorld().getGridSize();
    }

}
