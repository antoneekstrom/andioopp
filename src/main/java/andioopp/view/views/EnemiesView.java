package andioopp.view.views;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.*;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.transform.Transform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.model.Model;
import andioopp.model.enemy.Enemy;
import andioopp.model.world.World;
import andioopp.view.View;

public class EnemiesView<S extends Sprite<?>> extends EntityView implements View<S> {

    private static final float ENEMY_SIZE_BY_CELL_SCALE = 0.7f;
    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final TransformFactory transformFactory;

    public EnemiesView(Rectangle viewportRect, TransformFactory transformFactory) {
        super(viewportRect);
        this.transformFactory = transformFactory;
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        World world = model.getWorld();
        for (Enemy enemy : world.getEnemies()) {
            renderEnemy(renderer, world, enemy);
        }
    }

    private void renderEnemy(Renderer<S> renderer, World world, Enemy enemy) {
        S sprite = enemy.getSprite(renderer.getSpriteFactory());
        Rectangle enemyRect = getEntityRect(world, enemy.getPosition(), sprite);

        Dimension enemySize = getEnemySize(world, enemyRect);

        Vector3f enemyPosition = getEnemyPosition(enemyRect);
        Transform enemyTransform = transformFactory.createWithPosition(enemyPosition);

        renderer.drawSprite(sprite, enemyTransform, enemySize);
    }

    private Vector3f getEnemyPosition(Rectangle enemyRect) {
        return enemyRect.getPosition();
    }

    private Dimension getEnemySize(World world, Rectangle enemyRect) {
        Dimension cellRes = getCellSize(world);
        return enemyRect.getSize().setHeight(cellRes.getHeight() * ENEMY_SIZE_BY_CELL_SCALE);
    }
}
