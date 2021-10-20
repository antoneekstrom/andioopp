package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.system.System;

/**
 * A class that handles updating Projectiles.
 */
public class UpdateProjectileSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        for (Projectile projectile : model.getWorld().getProjectiles()) {
            projectile.update(time);
        }
    }
}
