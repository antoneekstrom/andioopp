package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.tower.Tower;
import andioopp.model.domain.tower.attack.attacks.Attack;
import andioopp.model.domain.world.Cell;
import andioopp.model.domain.world.World;
import andioopp.model.system.System;
import andioopp.model.util.ModelCoordinate;

/**
 * A class that handles performing attacks by Towers.
 */
public class PerformTowerAttackSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        World world = model.getWorld();

        for (int row = 0; row < world.getNumberOfLanes(); row++) {
            for (int col = 0; col < world.getNumberOfCellsInLanes(); col++) {
                Cell cell = world.getCell(col, row);
                if (!cell.hasTower()) {
                    continue;
                }
                Tower tower = cell.getTower();

                for (Attack attack : tower.getAttacks()) {
                    doAttack(model, time, world, col, row, attack);
                }
            }
        }
    }

    private void doAttack(Model model, Time time, World world, int col, int row, Attack attack) {
        if (!attack.isAvailableForAttack(time)) {
            return;
        }

        ModelCoordinate position = new ModelCoordinate(col, row);

        if (attack.getTargetArea().getClass().getSimpleName().equals("NonTargeting")) {
            attack.perform(time, model, position);
        }

        for (Enemy enemy : attack.getEnemiesInRange(world, position)) {
            if (enemy.canBeDamagedBy(attack)) {
                attack.perform(time, model, position);
            }
        }
    }
}
