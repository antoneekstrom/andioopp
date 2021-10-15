package andioopp.model.system;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;

public class UpdateEnemyService extends ModelService {

    @Override
    public void update(Model model, Time time) {
        for (Enemy enemy : model.getWorld().getEnemies()) {
            enemy.update(time);
        }
    }
}
