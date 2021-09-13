package andioopp.domain.view;

import andioopp.common.transform.ConcreteTransform;
import andioopp.common.transform.Transform;
import andioopp.common.transform.Vector3f;
import andioopp.domain.model.Lane;
import andioopp.domain.model.Model;
import andioopp.domain.model.enemy.Enemy;
import andioopp.gfx.Color;
import andioopp.gfx.Renderer;
import andioopp.gfx.Sprite;

public class View<S extends Sprite<?>> {

    private final Renderer<S> renderer;

    public View(Renderer<S> renderer) {
        this.renderer = renderer;
    }

    public void render(Model model) {
        getRenderer().clear(Color.WHITE);
        model.getWorld().getLanes().forEach(this::renderLane);
    }

    private void renderLane(Lane lane) {
        lane.getEnemies().forEach(enemy -> renderEnemy(lane, enemy));
    }

    private void renderEnemy(Lane lane, Enemy e) {
        renderer.drawSprite(e.getSprite(getRenderer().getSpriteFactory()), getEnemyScreenTransform(lane, e));
    }

    private Transform getEnemyScreenTransform(Lane lane, Enemy e) {
        Vector3f position = lane.getTransform().getPosition().add(e.getTransform().getPosition());
        return ConcreteTransform.getFactory().createWithPosition(position);
    }

    private Renderer<S> getRenderer() {
        return renderer;
    }
}
