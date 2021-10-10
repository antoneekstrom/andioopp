package andioopp.view;

import andioopp.common.transform.Dimension;
import andioopp.common.transform.ImmutableRectangle;
import andioopp.common.transform.Rectangle;
import andioopp.common.transform.Vector3f;
import andioopp.model.world.World;

public class CellView {

    private final Rectangle viewportRect;

    protected CellView(Rectangle viewportRect) {
        this.viewportRect = viewportRect;
    }

    protected Rectangle getCellRect(World world, int row, int col) {
        Vector3f position = getCellPosition(world, row, col);
        Dimension size = getCellSize(world);
        return new ImmutableRectangle(position, size);
    }

    protected Vector3f getCellPosition(World world, int row, int col) {
        return getViewport(world).toViewportPosition(new Vector3f(col, row));
    }

    protected Dimension getCellSize(World world) {
        return getViewport(world).toViewportSize(Dimension.unit());
    }

    protected Viewport getViewport(World world) {
        return new Viewport(viewportRect, world.getGridSize());
    }
}
