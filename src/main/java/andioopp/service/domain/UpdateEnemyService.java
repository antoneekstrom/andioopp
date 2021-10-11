package andioopp.service.domain;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.enemy.Enemy;

public class UpdateEnemyService extends DomainService {

    @Override
    public void update(Model model, Time time) {
        for (Enemy enemy : model.getWorld().getEnemies()) {
            enemy.update(time);
        }
    }
}
