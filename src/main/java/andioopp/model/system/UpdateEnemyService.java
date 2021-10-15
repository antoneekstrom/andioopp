package andioopp.model.system;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;

public class UpdateEnemyService implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        for (Enemy enemy : model.getWorld().getEnemies()) {
            // Enemy should stop moving if there is a tower in front of it
            if (!enemy.isTowerAhead()) {
                enemy.move();
            }
        }
    }
}
