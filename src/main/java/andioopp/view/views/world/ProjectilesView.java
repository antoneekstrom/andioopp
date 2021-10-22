package andioopp.view.views.world;

import andioopp.common.graphics.Renderer;
import andioopp.common.graphics.Sprite;
import andioopp.common.math.dimension.Dimension;
import andioopp.model.Model;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.domain.world.World;
import andioopp.view.View;
import andioopp.view.util.ModelViewport;
import andioopp.view.util.ViewCoordinate;
/**
 * A View for displaying Projectiles.
 */
public class ProjectilesView implements View<Model> {

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

    /**
     * Renders a sprite of a Projectile on its position in the game.
     */
    private <S extends Sprite<?>> void renderProjectile(Renderer<S> renderer, Projectile projectile) {
        S sprite = renderer.getSpriteFactory().get(projectile.getSprite());
        ViewCoordinate position = viewport.getPosition(projectile.getPosition());
        Dimension size = viewport.getSize(projectile.getSize());
        renderer.drawSprite(sprite, position, size);
    }
}
