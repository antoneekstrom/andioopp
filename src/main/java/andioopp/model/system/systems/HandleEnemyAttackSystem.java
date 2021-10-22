package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.world.Cell;
import andioopp.model.domain.world.World;
import andioopp.model.system.System;

/**
 * A class that handles attacks performed by Enemies.
 */
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
                    if (!isTowerWithinRange(enemy, col, row)) {
                        continue;
                    }

                    Cell cell = world.getCell(col, row);
                    if (cell.hasTower()) {
                        Tower tower = cell.getTower();

                        enemy.setTowerAhead(true);
                        if (enemy.canAttack(time)) {
                            enemy.attack(time, tower);
                        }

                        if (tower.getHealth().isDead()) {
                            cell.setTower(null);
                        }
                    }
                }
            }
        }
    }

    private boolean isTowerWithinRange(Enemy enemy, int col, int row) {
        float deltaX = enemy.getPosition().getX() - col;
        boolean closeX = deltaX < 0.5f && deltaX > 0;
        boolean closeY = enemy.getRow() == row;
        return closeX && closeY;
    }
}
