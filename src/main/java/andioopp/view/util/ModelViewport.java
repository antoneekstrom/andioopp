package andioopp.view.util;

import andioopp.common.math.Dimension;
import andioopp.common.math.Vector3f;
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

    public ViewCoordinate getCenterWithinCell(int row, int col) {
        throw new NotImplementedException();
    }

    public ViewCoordinate getViewCoordinate(ModelCoordinate modelCoordinate) {
        return new ViewCoordinate(viewport.getPositionOutside(modelCoordinate));
    }

    public Dimension getViewSize(Dimension modelSize) {
        return new Dimension(new ViewCoordinate(viewport.getSizeOutside(modelSize.toVector())));
    }

    private Dimension getInsideDimensionFromModel(Model model) {
        return model.getWorld().getGridSize();
    }

}
