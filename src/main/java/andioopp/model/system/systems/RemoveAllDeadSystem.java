package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.system.System;

public class RemoveAllDeadSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        removeDeadEnemies(model);

        removeDeadProjectiles(model);
    }

    private void removeDeadEnemies(Model model) {
        model.getWorld().getEnemies().removeIf(enemy -> {
            boolean isDead = enemy.isDead();
            if(isDead) {
                model.getWorld().getDroppedCoins().add(new DroppedCoinEntity(enemy.getPosition(), enemy.getReward()));
            }
            return isDead;
        });
    }

    private void removeDeadProjectiles(Model model) {
        model.getWorld().getProjectiles().removeIf(projectile -> {
            boolean isDead = projectile.getHealth().isDead();
            if(isDead) {

            }
            return isDead;
        });
    }
}
