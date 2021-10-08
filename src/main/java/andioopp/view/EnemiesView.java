package andioopp.view;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.enemy.Enemy;
import andioopp.model.world.World;

public class EnemiesView<S extends Sprite<?>> {

    private static final float ENEMY_SIZE_BY_CELL_SCALE = 0.7f;
    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final Renderer<S> renderer;
    private final CellsView<S> cellsView;
    private final TransformFactory transformFactory;
    private final Rectangle viewport;

    public EnemiesView(Renderer<S> renderer, CellsView<S> cellsView, TransformFactory transformFactory, Rectangle viewport) {
        this.renderer = renderer;
        this.cellsView = cellsView;
        this.transformFactory = transformFactory;
        this.viewport = viewport;
    }

    public void render(World world) {
        for (Enemy enemy : world.getEnemies()) {
            renderEnemy(world, enemy);
        }
    }

    private void renderEnemy(World world, Enemy enemy) {
        S enemySprite = enemy.getSprite(getRenderer().getSpriteFactory());
        Dimension enemyScreenSize = getEnemyScreenSize(world, enemySprite);
        Transform enemyScreenTransform = transformFactory.createWithPosition(getEntityScreenPosition(world, enemy.getPosition(), enemyScreenSize));
        getRenderer().drawSprite(enemySprite, enemyScreenTransform, enemyScreenSize.toVector());
    }

    private Vector3f alignWithCellBottom(World world, Dimension size) {
        Dimension cellScreenSize = cellsView.getCellScreenSize(world);
        Vector3f cellScreenPositionCenter = cellScreenSize.centerWithin(Vector3f.zero(), size);
        Vector3f offsetToBottom = cellScreenSize.toVector().onlyY().scale(0.5f).sub(size.toVector().onlyY().scale(0.5f));
        return cellScreenPositionCenter.add(offsetToBottom);
    }

    private Vector3f getEntityScreenPosition(World world, Vector3f position, Dimension size) {
        return getViewportPosition().add(position.scale(cellsView.getCellScreenSize(world).toVector())).add(alignWithCellBottom(world, size)).add(getEntityCellOffset(world));
    }

    private Vector3f getEntityCellOffset(World world) {
        return cellsView.getCellScreenSize(world).toVector().onlyY().scale(TOWER_CELL_OFFSET_PERCENT);
    }

    private Dimension getEnemyScreenSize(World world, S enemySprite) {
        Dimension cellScreenSize = cellsView.getCellScreenSize(world);
        Dimension enemySpriteSize = enemySprite.getSize();
        return enemySpriteSize.scaleByHeight(cellScreenSize.getHeight() * ENEMY_SIZE_BY_CELL_SCALE);
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
