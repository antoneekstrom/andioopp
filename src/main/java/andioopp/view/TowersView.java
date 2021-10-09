package andioopp.view;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.Model;
import andioopp.model.tower.Tower;
import andioopp.model.world.World;

public class TowersView<S extends Sprite<?>> extends CellView implements View<S> {

    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final TransformFactory transformFactory;

    public TowersView(Rectangle viewportRect, TransformFactory transformFactory) {
        super(viewportRect);
        this.transformFactory = transformFactory;
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        World world = model.getWorld();
        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                renderTower(renderer, world, row, col);
            }
        }
    }

    private void renderTower(Renderer<S> renderer, World world, int row, int col) {
        Tower tower = world.getCell(row, col).getTower();

        if (tower != null) {
            S towerSprite = tower.getSprite(renderer.getSpriteFactory());
            Dimension towerSize = getTowerSize(world, towerSprite);

            Vector3f towerPosition = getTowerPosition(world, row, col);
            Transform towerTransform = transformFactory.createWithPosition(towerPosition);

            renderer.drawSprite(towerSprite, towerTransform, towerSize);
        }
    }

    private Vector3f getTowerPosition(World world, int row, int col) {
        Rectangle cellRect = getCellRect(world, row, col);
        return cellRect.getPosition().add(cellRect.getSize().toVector().scale(Vector3f.withY(TOWER_CELL_OFFSET_PERCENT)));
    }

    private Dimension getTowerSize(World world, S sprite) {
        Dimension cellRes = getCellSize(world);
        return sprite.getSize().scaleByHeight(cellRes.getHeight());
    }
}
