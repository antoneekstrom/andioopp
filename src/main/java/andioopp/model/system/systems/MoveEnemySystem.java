package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.system.System;

/**
 * A class that handles moving Enemies.
 */
public class MoveEnemySystem implements System<Model> {
    @Override
    public void update(Model model, Time time) {
        for (Enemy enemy : model.getWorld().getEnemies()) {
            if (!enemy.isTowerAhead()) {
                enemy.move(time);
            }
        }
    }
}
