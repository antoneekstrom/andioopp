package andioopp.view.util;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;
import andioopp.model.util.ModelCoordinate;
import andioopp.model.Model;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Specifically translates model coordinates to window coordinates.
 */
public class ModelViewport {

    private final Viewport viewport;

    public ModelViewport(Model model, Dimension outsideDimension, Vector3f outsideOffset) {
        viewport = new Viewport(getInsideDimensionFromModel(model), outsideDimension, outsideOffset);
    }

    public ViewCoordinate getCenterWithinCell(int col, int row) {
        throw new NotImplementedException();
    }

    public ViewCoordinate getViewCoordinate(ModelCoordinate modelCoordinate) {
        return new ViewCoordinate(viewport.getPositionOutside(modelCoordinate));
    }

    public Dimension getViewSize(Dimension modelSize) {
        return new Dimension(viewport.getSizeOutside(modelSize));
    }

    private Dimension getInsideDimensionFromModel(Model model) {
        return model.getWorld().getGridSize();
    }

}
