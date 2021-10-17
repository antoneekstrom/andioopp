package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.world.Cell;
import andioopp.model.domain.world.World;
import andioopp.model.system.System;

public class HandleEnemyAttackSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        handleEnemyAttacks(time, model.getWorld());
    }

    private void handleEnemyAttacks(Time time, World world) {
        for (Enemy enemy : world.getEnemies()) {
            enemy.setTowerAhead(false);

            for (int row = 0; row < world.getNumberOfLanes(); row++) {
                for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                    Cell cell = world.getCell(row, col);
                    if (cell.hasTower()) {
                        Tower tower = cell.getTower();
                        float deltaX = enemy.getPosition().getX() - col;
                        if (deltaX < 0.5f && deltaX > 0) {
                            enemy.setTowerAhead(true);
                            if (enemy.canAttack(time)) {
                                enemy.setTimeOfLastAttack(time);
                                tower.getHealth().decrease(1);
                            }
                        }
                        if (tower.getHealth().isZero()) {
                            cell.setTower(null);
                        }
                    }
                }
            }
        }
    }
}
