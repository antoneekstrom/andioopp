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

public class TowersView implements View<Model> {

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

    private <S extends Sprite<?>> void renderTower(Renderer<S> renderer, World world, int col, int row) {
        Cell cell = world.getCell(col, row);
        if (!cell.hasTower()) {
            return;
        }

        Tower tower = cell.getTower();
        S sprite = renderer.getSpriteFactory().get(tower.getSprite());

        renderer.drawSprite(sprite, getTowerRectangle(sprite, col, row));
    }

    private Rectangle getTowerRectangle(Sprite<?> sprite, int col, int row) {
        Rectangle cellRect = getCellRect(col, row);
        RectangleBuilder builder = new RectangleBuilder(cellRect);

        builder.setSize(getTowerSizeFromSprite(sprite));
        builder.alignCenterWithX(cellRect.getCenter().getX());
        builder.alignBottomWithY(cellRect.getBottomRightCorner().getY());
        builder.translate(cellRect.getSize().toVector().fromY().negate().scale(TOWER_CELL_OFFSET_PERCENT));

        return builder.build();
    }

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
