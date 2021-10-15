package andioopp.model.system;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.tower.attack.projectiles.Projectile;

public class UpdateProjectileService implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        for (Projectile projectile : model.getWorld().getProjectiles()) {
            projectile.update(time);
        }
    }
}
