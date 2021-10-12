package andioopp.view;

import andioopp.common.gfx.Color;
import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.Dimension;
import andioopp.common.transform.Rectangle;
import andioopp.common.transform.Vector3f;
import andioopp.model.world.Lane;
import andioopp.model.world.World;

public class CellsView<S extends Sprite<?>> {

    private static final Color COLOR_CELL_ODD = new Color(112, 146, 85);
    private static final Color COLOR_CELL_EVEN = new Color(62, 86, 34);

    private final Renderer<S> renderer;
    private final Rectangle viewport;

    public CellsView(Renderer<S> renderer, Rectangle viewport) {
        this.renderer = renderer;
        this.viewport = viewport;
    }

    public void render(World world) {
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                renderCell(world, row, col);
            }
        }
    }

    private void renderCell(World world, int row, int col) {
        Vector3f position = getCellScreenPosition(world, row, col);
        Dimension size = getCellScreenSize(world);
        Color cellColor = getCellColor(row, col);
        getRenderer().drawRectangle(position, size, cellColor);
    }

    public Vector3f getCellScreenPosition(World world, int row, int col) {
        Dimension cellScreenSize = getCellScreenSize(world);
        Vector3f laneScreenPosition = getLaneScreenPosition(world, row);
        Vector3f cellScreenOffset = cellScreenSize.toVector().onlyX().scale(col);
        return laneScreenPosition.add(cellScreenOffset);
    }

    public Rectangle getCellScreenRectangle(World world, int row, int col) {
        return new Rectangle(getCellScreenPosition(world, row, col), getCellScreenSize(world));
    }

    public Dimension getCellScreenSize(World world) {
        return new Dimension(getCellScreenWidth(world.getLane(0)), getLaneScreenHeight(world));
    }

    private float getLaneScreenWidth() {
        return getViewportSize().getWidth();
    }

    private float getLaneScreenHeight(World world) {
        return getViewportSize().getHeight() / world.getNumberOfLanes();
    }

    private float getCellScreenWidth(Lane lane) {
        return getLaneScreenWidth() / lane.getNumberOfCells();
    }

    private Vector3f getLaneScreenPosition(World world, int row) {
        Vector3f laneOffset = Vector3f.withY(getLaneScreenHeight(world) * row);
        return getViewportPosition().add(laneOffset);
    }

    private Color getCellColor(int row, int col) {
        if (row % 2 == col % 2) {
            return COLOR_CELL_EVEN;
        }
        return COLOR_CELL_ODD;
    }

    private Vector3f getViewportPosition() {
        return getViewport().getPosition();
    }

    private Dimension getViewportSize() {
        return getViewport().getSize();
    }

    private Rectangle getViewport() {
        return viewport;
    }

    private Renderer<S> getRenderer() {
        return renderer;
    }
}
