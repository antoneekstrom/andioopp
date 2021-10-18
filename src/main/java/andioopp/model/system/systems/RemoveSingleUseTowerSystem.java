package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.world.Cell;
import andioopp.model.domain.world.World;
import andioopp.model.system.System;

public class RemoveSingleUseTowerSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        World world = model.getWorld();
        checkHealthStates(world);
    }

    private void checkHealthStates(World world) {
        checkEnemiesHealthState(world);
        checkTowersHealthState(world);
    }

    private void checkTowersHealthState(World world) {
        //Goes through all cells and its towers and removes the tower if its health is zero.
        for (Cell cell : world.getCells()) {
            if(cell.hasTower() && cell.getTower().getHealth().isDead()) {
                //This removes the tower.
                cell.setTower(null);
            }
        }
    }

    private void checkEnemiesHealthState(World world) {
        //removes enemy if its health is zero
        world.getEnemies().removeIf(enemy -> enemy.getHealth().isDead());
    }
}
