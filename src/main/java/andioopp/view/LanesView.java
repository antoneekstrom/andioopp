package andioopp.view;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.model.Model;
import andioopp.model.world.World;

public class LanesView<S extends Sprite<?>> extends CellView implements View<S> {

    private static final Color COLOR_CELL_ODD = new Color(112, 146, 85);
    private static final Color COLOR_CELL_EVEN = new Color(62, 86, 34);

    public LanesView(Rectangle viewportRect) {
        super(viewportRect);
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        World world = model.getWorld();
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                renderCell(renderer, world, row, col);
            }
        }
    }

    @Override
    public Rectangle getCellRect(World world, int row, int col) {
        return super.getCellRect(world, row, col);
    }

    private void renderCell(Renderer<S> renderer, World world, int row, int col) {
        Rectangle cellRect = getCellRect(world, row, col);
        renderer.drawRectangle(cellRect.getPosition(), cellRect.getSize(), getCellColor(row, col));
    }

    private Color getCellColor(int row, int col) {
        if (row % 2 == col % 2) {
            return COLOR_CELL_EVEN;
        }
        return COLOR_CELL_ODD;
    }
}
