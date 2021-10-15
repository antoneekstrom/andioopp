package andioopp.view.views.world;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.model.util.ModelCoordinate;
import andioopp.model.Model;
import andioopp.model.domain.world.World;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

public class LanesView implements View<Model> {

    private static final Color COLOR_CELL_ODD = new Color(112, 146, 85);
    private static final Color COLOR_CELL_EVEN = new Color(62, 86, 34);

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer, ModelViewport viewport) {
        World world = model.getWorld();
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            renderLane(renderer, viewport, world, row);
        }
    }

    private <S extends Sprite<?>> void renderLane(Renderer<S> renderer, ModelViewport viewport, World world, int row) {
        for (int i = 0; i < world.getNumberOfCellsInLanes(); i++) {
            renderCell(renderer, viewport, row, i);
        }
    }

    private <S extends Sprite<?>> void renderCell(Renderer<S> renderer, ModelViewport viewport, int row, int col) {
        ViewCoordinate viewPosition = viewport.getViewCoordinate(new ModelCoordinate(row, col));
        Dimension<ViewCoordinate> viewSize = viewport.getViewSize(new Dimension<>(new ModelCoordinate(1, 1)));
        Color cellColor = getCellColor(row, col);
        renderer.drawRectangle(viewPosition, viewSize, cellColor);
    }

    private Color getCellColor(int row, int col) {
        if (row % 2 == col % 2) {
            return COLOR_CELL_EVEN;
        }
        return COLOR_CELL_ODD;
    }
}
