package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.Model;
import andioopp.model.domain.world.World;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;

public class ProjectilesView implements View<Model> {

    private static final float TOWER_CELL_OFFSET_PERCENT = -0.3f;

    private final ModelViewport viewport;

    public ProjectilesView(ModelViewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public <S extends Sprite<?>> void render(Model model, Renderer<S> renderer) {
        World world = model.getWorld();
        for (Projectile projectile : world.getProjectiles()) {
            renderProjectile(renderer, projectile);
        }
    }

    private <S extends Sprite<?>> void renderProjectile(Renderer<S> renderer, Projectile projectile) {
        S sprite = renderer.getSpriteFactory().get(projectile.getSprite());
        ViewCoordinate position = viewport.getViewCoordinate(projectile.getPosition());
        Dimension size = viewport.getViewSize(projectile.getSize());
        renderer.drawSprite(sprite, position, size);
    }
}
