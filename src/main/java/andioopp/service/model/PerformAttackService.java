package andioopp.service.model;

import andioopp.common.time.Time;
import andioopp.common.math.Vector3f;
import andioopp.model.Model;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.world.World;

public class PerformAttackService extends ModelService {

    @Override
    public void update(Model model, Time time) {
        performAttacks(model, time);
    }

    private void performAttacks(Model model, Time time) {
        World world = model.getWorld();
        for (int row = 0; row < world.getLanes().size(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                Tower tower = world.getCell(row, col).getTower();
                if (tower != null) {
                    for (Attack attack : tower.getAttacks()) {
                        if (!attack.isAvailableForAttack(time)) {
                            continue;
                        }

                        Vector3f position = new Vector3f(col, row);
                        for (Enemy enemy : attack.getEnemiesInRange(world, position)) {
                            if (enemy.canBeDamagedBy(attack)) {
                                attack.performAttack(model, position);
                                attack.updateTimeOfLastAttack(time);
                            }
                        }
                    }
                }
            }
        }
    }
}
