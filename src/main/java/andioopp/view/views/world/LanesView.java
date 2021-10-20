package andioopp.view.views.world;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.model.util.ModelCoordinate;
import andioopp.model.Model;
import andioopp.model.domain.world.World;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

/**
 * A View for displaying Lanes.
 */
public class LanesView implements View<Model> {
    /**
     * Colors used for the cells.
     */
    private static final Color COLOR_CELL_ODD = new Color(112, 146, 85);
    private static final Color COLOR_CELL_EVEN = new Color(62, 86, 34);

    private final ModelViewport viewport;

    public LanesView(ModelViewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        World world = model.getWorld();
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            renderLane(renderer, world, row);
        }
    }

    /**
     * Uses a renderer to render a Lane consisting of Cells on the screen.
     */
    private <S extends Sprite<?>> void renderLane(Renderer<S> renderer, World world, int row) {
        for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
            renderCell(renderer, col, row);
        }
    }

    /**
     * Uses a renderer to render a Cell as a rectangle on the screen in a color.
     */
    private <S extends Sprite<?>> void renderCell(Renderer<S> renderer, int col, int row) {
        ViewCoordinate position = getCellPosition(col, row);
        Dimension size = getCellSize();
        Color cellColor = getCellColor(col, row);
        renderer.drawRectangle(position, size, cellColor);
    }

    /**
     * @return a Dimension for a Cell.
     */
    public Dimension getCellSize() {
        return viewport.getSize(Dimension.UNIT);
    }

    /**
     * Returns a view-position as a ViewCoordinate for a cell.
     * @param col column of the cell.
     * @param row row for the cell.
     * @return a ViewCoordinate for a cell.
     */
    public ViewCoordinate getCellPosition(int col, int row) {
        return viewport.getPosition(new ModelCoordinate(col, row));
    }


    private Color getCellColor(int col, int row) {
        if (row % 2 == col % 2) {
            return COLOR_CELL_EVEN;
        }
        return COLOR_CELL_ODD;
    }
}
