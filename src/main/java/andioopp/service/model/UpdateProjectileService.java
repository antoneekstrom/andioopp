package andioopp.service.model;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.tower.attack.projectiles.Projectile;

public class UpdateProjectileService extends ModelService {

    @Override
    public void update(Model model, Time time) {
        for (Projectile projectile : model.getWorld().getProjectiles()) {
            projectile.update(time);
        }
    }
}
