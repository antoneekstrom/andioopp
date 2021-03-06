package andioopp.view.views.world;

import andioopp.common.graphics.Color;
import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.vector.Vector3f;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
/**
 * A View for displaying Lanes.
 */
public class EnemiesView implements View<Model> {
    /**
     * Floats used for adjusting the position and size of a Tower in a Cell.
     */
    private static final float SIZE_PERCENTAGE_OF_CELL_HEIGHT = 1f;
    private static final float OFFSET_FROM_CELL_BOTTOM = 0.4f;

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

    /**
     * Uses a renderer to render Enemies on the screen on the enemies current position.
     */
    private <S extends Sprite<?>> void renderEnemy(Renderer<S> renderer, Enemy enemy) {
        S sprite = enemy.getSprite(renderer.getSpriteFactory());

        Dimension cellSize = viewport.getCellSize();
        Dimension size = sprite.getSize().setHeight(cellSize.getHeight() * SIZE_PERCENTAGE_OF_CELL_HEIGHT);
        Vector3f position = viewport.getPosition(enemy.getPosition());
        Vector3f alignedWithCellBottom = position.add(cellSize.toVector().fromY().sub(size.toVector().fromY()));
        Vector3f withAddedOffset = alignedWithCellBottom.sub(cellSize.toVector().fromY().scale(OFFSET_FROM_CELL_BOTTOM));

        renderer.drawSprite(sprite, withAddedOffset, size);
    }
}
