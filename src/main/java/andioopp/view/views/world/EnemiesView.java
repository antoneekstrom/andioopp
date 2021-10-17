package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

public class EnemiesView implements View<Model> {

    private static final float ENEMY_SIZE_BY_CELL_SCALE = 0.7f;
    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final ModelViewport viewport;

    public EnemiesView(ModelViewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        for (Enemy enemy : model.getWorld().getEnemies()) {
            renderEnemy(renderer, enemy);
        }
    }

    private <S extends Sprite<?>> void renderEnemy(Renderer<S> renderer, Enemy enemy) {
        S sprite = enemy.getSprite(renderer.getSpriteFactory());

        ViewCoordinate viewPosition = viewport.getViewCoordinate(enemy.getPosition());
        Dimension viewSize = viewport.getViewSize(enemy.getSize());

        renderer.drawSprite(sprite, viewPosition, viewSize);
    }
}
