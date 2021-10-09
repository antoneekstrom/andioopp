package andioopp.view;

import andioopp.common.gfx.Renderer;
import andioopp.common.gfx.Sprite;
import andioopp.common.transform.*;
import andioopp.model.Model;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.attack.projectiles.Projectile;
import andioopp.model.world.World;

public class ProjectilesView<S extends Sprite<?>> extends EntityView implements View<S> {

    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final TransformFactory transformFactory;

    public ProjectilesView(Rectangle viewportRect, TransformFactory transformFactory) {
        super(viewportRect);
        this.transformFactory = transformFactory;
    }

    @Override
    public void render(Renderer<S> renderer, Model model) {
        World world = model.getWorld();
        for (Projectile projectile : world.getProjectiles()) {
            renderProjectile(renderer, world, projectile);
        }
    }

    private void renderProjectile(Renderer<S> renderer, World world, Projectile projectile) {
        S sprite = renderer.getSpriteFactory().get(projectile.getSpritePath());
        Rectangle enemyRect = getEntityRect(world, projectile.getPosition(), sprite);
        Transform enemyTransform = transformFactory.createWithPosition(enemyRect.getPosition());
        renderer.drawSprite(sprite, enemyTransform, enemyRect.getSize());
    }
}
