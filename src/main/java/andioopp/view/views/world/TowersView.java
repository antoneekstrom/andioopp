package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.model.Model;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.world.Cell;
import andioopp.model.domain.world.World;
import andioopp.model.util.ModelCoordinate;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

public class TowersView implements View<Model> {

    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final ModelViewport viewport;

    public TowersView(ModelViewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        World world = model.getWorld();
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                renderTower(renderer, world, row, col);
            }
        }
    }

    private <S extends Sprite<?>> void renderTower(Renderer<S> renderer, World world, int row, int col) {
        Cell cell = world.getCell(row, col);
        if (!cell.hasTower()) {
            return;
        }

        Tower tower = cell.getTower();
        S sprite = renderer.getSpriteFactory().get(tower.getSprite());

        ViewCoordinate position = viewport.getViewCoordinate(new ModelCoordinate(row, col));
        Dimension size = getTowerSize(tower);

        renderer.drawSprite(sprite, position, size);
    }

    public Dimension getTowerSize(Tower tower) {
        return viewport.getViewSize(tower.getSize());
    }
}
