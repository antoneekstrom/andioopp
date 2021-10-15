package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.Dimension;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.transform.Transform;
import andioopp.common.math.transform.TransformFactory;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.Model;
import andioopp.model.domain.world.World;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;

public class ProjectilesView<S extends Sprite<?>> extends EntityView implements View<S> {

    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final TransformFactory transformFactory;

    public ProjectilesView(Rectangle viewportRect, TransformFactory transformFactory) {
        super(viewportRect);
        this.transformFactory = transformFactory;
    }


    private void renderProjectile(Renderer<S> renderer, World world, Projectile projectile) {
        S sprite = renderer.getSpriteFactory().get(projectile.getSpritePath());
        Rectangle enemyRect = getEntityRect(world, projectile.getPosition(), sprite);
        Transform enemyTransform = transformFactory.createWithPosition(enemyRect.getPosition());
        renderer.drawSprite(sprite, enemyTransform, enemyRect.getSize());
    }

    @Override
    protected Dimension getEntitySize(World world, Sprite<?> sprite) {
        return new Dimension(super.getEntitySize(world, sprite).toVector().scale(0.7f));
    }

    @Override
    public <S1 extends Sprite<?>> void render(S model, Renderer<S1> renderer, ModelViewport viewport) {
        World world = model.getWorld();
        for (Projectile projectile : world.getProjectiles()) {
            renderProjectile(renderer, world, projectile);
        }
    }
}
