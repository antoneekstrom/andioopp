package andioopp.model.system;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.world.World;

public class HandleEnemyAttackService extends ModelService {

    @Override
    public void update(Model model, Time time) {
        handleEnemyAttacks(time, model.getWorld());
    }

    private void handleEnemyAttacks(Time time, World world) {
        for (Enemy enemy : world.getEnemies()) {
            int row = (int) enemy.getPosition().getY();

            enemy.setTowerAhead(false);
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                Tower tower = world.getCell(row, col).getTower();

                if (tower != null) {
                    float deltaX = enemy.getPosition().getX() - col;
                    if (deltaX < 0.5f && deltaX > 0) {
                        enemy.setTowerAhead(true);
                        if (enemy.canAttack(time)) {
                            enemy.setTimeOfLastAttack(time);
                            tower.getHealth().decrease(1);
                        }
                    }
                    if (tower.getHealth().isZero()) {
                        world.getCell(row, col).setTower(null);
                    }
                }
            }
        }
    }
}
