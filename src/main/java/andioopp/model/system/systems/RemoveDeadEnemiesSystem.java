package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.system.System;

public class RemoveDeadEnemiesSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        model.getWorld().getEnemies().removeIf(enemy -> {
            boolean isDead = enemy.isDead();
            if(isDead) {
                model.getWorld().getDroppedCoins().add(new DroppedCoinEntity(enemy.getPosition(), enemy.getReward()));
            }
            return enemy.isDead();
        });
    }
}
