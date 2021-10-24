package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.rectangle.RectangleBuilder;
import andioopp.common.math.vector.Vector3f;
import andioopp.model.Model;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.world.Cell;
import andioopp.model.domain.world.World;
import andioopp.model.util.ModelCoordinate;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

/**
 * A View for displaying TowerFactory.
 */

public class TowersView implements View<Model> {

    /**
     * Floats used for adjusting the position and size of a Tower in a Cell.
     */
    private static final float SIZE_PERCENTAGE_OF_CELL_HEIGHT = 1f;
    private static final float TOWER_CELL_OFFSET_PERCENT = 0.4f;

    private final ModelViewport viewport;

    public TowersView(ModelViewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        World world = model.getWorld();
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                renderTower(renderer, world, col, row);
            }
        }
    }

    /**
     * Renders a sprite of a Tower in a specific cell.
     */
    private <S extends Sprite<?>> void renderTower(Renderer<S> renderer, World world, int col, int row) {
        Cell cell = world.getCell(col, row);
        if (!cell.hasTower()) {
            return;
        }

        Tower tower = cell.getTower();
        S sprite = renderer.getSpriteFactory().get(tower.getSprite());

        renderer.drawSprite(sprite, getTowerRectangle(sprite, col, row));
    }

    /**
     * Uses a RectangleBuilder to build a rectangle with the same position and size as a sprite of a Tower.
     * @param sprite sprite of a Tower.
     * @param col the cells column for where the Tower is placed.
     * @param row the cells row for where the Tower is placed.
     * @return a Rectangle with the same position and size as a sprite of a Tower.
     */
    private Rectangle getTowerRectangle(Sprite<?> sprite, int col, int row) {
        Rectangle cellRect = getCellRect(col, row);
        RectangleBuilder builder = new RectangleBuilder(cellRect);

        builder.setSize(getTowerSizeFromSprite(sprite));
        builder.alignCenterWithX(cellRect.getCenter().getX());
        builder.alignBottomWithY(cellRect.getBottomRightCorner().getY());
        builder.translate(cellRect.getSize().toVector().fromY().negate().scale(TOWER_CELL_OFFSET_PERCENT));

        return builder.build();
    }

    /**
     * Creates a Dimension for a TowerFactory sprite when being placed in a cell.
     * @param sprite the sprite used for displaying a tower.
     * @return Dimension for TowerFactory placed in a cell.
     */

    public Dimension getTowerSizeFromSprite(Sprite<?> sprite) {
        Dimension spriteSize = sprite.getSize();
        float spriteHeight = getCellSize().getHeight() * SIZE_PERCENTAGE_OF_CELL_HEIGHT;
        return spriteSize.setHeight(spriteHeight);
    }

    private ImmutableRectangle getCellRect(int col, int row) {
        return new ImmutableRectangle(getCellPosition(col, row), getCellSize());
    }

    private ViewCoordinate getCellPosition(int col, int row) {
        return viewport.getPosition(new ModelCoordinate(col, row));
    }

    private Dimension getCellSize() {
        return viewport.getSize(Dimension.UNIT);
    }
}
