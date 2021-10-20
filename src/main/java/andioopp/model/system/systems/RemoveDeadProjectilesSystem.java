package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.system.System;

public class RemoveDeadProjectilesSystem implements System<Model> {
    @Override
    public void update(Model model, Time time) {
        removeDeadProjectiles(model);
    }

    private void removeDeadProjectiles(Model model) {
        model.getWorld().getProjectiles().removeIf(projectile -> projectile.getHealth().isDead());
    }
}
