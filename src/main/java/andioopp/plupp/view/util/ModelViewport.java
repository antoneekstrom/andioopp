package andioopp.plupp.view.util;

import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
import andioopp.model.Model;
import andioopp.plupp.model.util.ModelCoordinate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Specifically translates model coordinates to window coordinates.
 */
public class ModelViewport {

    private final Viewport viewport;

    public ModelViewport(Model model, Dimension outsideDimension, Vector3f outsideOffset) {
        viewport = new Viewport(getInsideDimensionFromModel(model), outsideDimension, outsideOffset);
    }

    public ViewCoordinate getCenterWithinCell(int row, int col) {
        throw new NotImplementedException();
    }

    public ViewCoordinate getViewCoordinate(ModelCoordinate modelCoordinate) {
        return new ViewCoordinate(viewport.getPositionOutside(modelCoordinate));
    }

    private Dimension getInsideDimensionFromModel(Model model) {
        return model.getWorld().getGridSize();
    }

}
