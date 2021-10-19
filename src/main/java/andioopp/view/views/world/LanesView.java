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

public class LanesView implements View<Model> {

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

    private <S extends Sprite<?>> void renderLane(Renderer<S> renderer, World world, int row) {
        for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
            renderCell(renderer, col, row);
        }
    }

    private <S extends Sprite<?>> void renderCell(Renderer<S> renderer, int col, int row) {
        ViewCoordinate position = getCellPosition(col, row);
        Dimension size = getCellSize();
        Color cellColor = getCellColor(col, row);
        renderer.drawRectangle(position, size, cellColor);
    }

    public Dimension getCellSize() {
        return viewport.getSize(Dimension.UNIT);
    }

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
