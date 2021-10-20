package andioopp.view.util;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.vector.Vector3f;
import andioopp.model.util.ModelCoordinate;
import andioopp.model.Model;

/**
 * Translates modelcoordinates to viewcoordinates.
 *
 * @author Anton Ekström
 * @see ModelCoordinate
 * @see ViewCoordinate
 * @see Viewport
 * @see Model
 */
public class ModelViewport {

    private final Viewport viewport;

    /**
     * Creates a modelviewport.
     *
     * @param model the model
     * @param outsideDimension the size of the viewport in the view
     * @param outsideOffset the position of the viewport in the view
     */
    public ModelViewport(Model model, Dimension outsideDimension, Vector3f outsideOffset) {
        viewport = new Viewport(getInsideDimensionFromModel(model), outsideDimension, outsideOffset);
    }

    /**
     * Returns the size of a {@link andioopp.model.domain.world.Cell}.
     *
     * @return the size
     */
    public Dimension getCellSize() {
        return getSize(Dimension.UNIT);
    }

    /**
     * Translates a position in the model to a position in the view.
     *
     * @param modelCoordinate the position in the model
     * @return the corresponding position in the view
     */
    public ViewCoordinate getPosition(ModelCoordinate modelCoordinate) {
        return new ViewCoordinate(viewport.getPositionOutside(modelCoordinate));
    }

    /**
     * Translates a dimension in the model to a dimension in the view.
     *
     * @param modelSize the dimension in the model
     * @return the dimension
     */
    public Dimension getSize(Dimension modelSize) {
        return new Dimension(viewport.getSizeOutside(modelSize));
    }

    private Dimension getInsideDimensionFromModel(Model model) {
        return model.getWorld().getGridSize();
    }

}
